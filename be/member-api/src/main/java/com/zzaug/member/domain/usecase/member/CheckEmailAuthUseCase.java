package com.zzaug.member.domain.usecase.member;

import static com.zzaug.member.domain.model.auth.EmailAuthResult.NOT_MATCH_CODE;
import static com.zzaug.member.domain.model.auth.EmailAuthResult.SUCCESS;

import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.dto.member.SuccessCheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.event.AddEmailEvent;
import com.zzaug.member.domain.exception.DBSource;
import com.zzaug.member.domain.exception.InvalidRequestException;
import com.zzaug.member.domain.exception.OverMaxTryCountException;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.member.domain.external.dao.auth.EmailAuthDao;
import com.zzaug.member.domain.external.dao.log.EmailAuthLogDao;
import com.zzaug.member.domain.external.dao.member.AuthenticationDao;
import com.zzaug.member.domain.external.dao.member.ExternalContactDao;
import com.zzaug.member.domain.external.security.AuthTokenValidator;
import com.zzaug.member.domain.external.security.auth.BlackTokenAuthCommand;
import com.zzaug.member.domain.external.security.auth.ReplaceTokenCacheService;
import com.zzaug.member.domain.external.service.auth.EmailAuthLogService;
import com.zzaug.member.domain.external.service.member.MemberContactsQuery;
import com.zzaug.member.domain.external.service.member.MemberSourceQuery;
import com.zzaug.member.domain.model.auth.EmailAuthSource;
import com.zzaug.member.domain.model.auth.TryCountElement;
import com.zzaug.member.domain.model.member.MemberAuthentication;
import com.zzaug.member.domain.model.member.MemberContacts;
import com.zzaug.member.domain.model.member.MemberSource;
import com.zzaug.member.domain.support.entity.EmailAuthSourceConverter;
import com.zzaug.member.domain.support.entity.MemberAuthenticationConverter;
import com.zzaug.member.entity.auth.EmailAuthEntity;
import com.zzaug.member.entity.auth.EmailData;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.entity.member.AuthenticationEntity;
import com.zzaug.member.entity.member.ContactType;
import com.zzaug.member.entity.member.ExternalContactEntity;
import com.zzaug.member.persistence.support.transaction.MemberSecurityChainedTransactional;
import com.zzaug.member.redis.email.EmailAuthSession;
import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.token.AuthToken;
import com.zzaug.security.token.TokenGenerator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckEmailAuthUseCase {

	private static final int MAX_TRY_COUNT = 3;

	private final AuthenticationDao authenticationDao;
	private final ExternalContactDao externalContactDao;
	private final EmailAuthDao emailAuthDao;
	private final EmailAuthLogDao emailAuthLogDao;

	private final MemberSourceQuery memberSourceQuery;
	private final EmailAuthLogService emailAuthLogService;
	private final MemberContactsQuery memberContactsQuery;

	private final ApplicationEventPublisher applicationEventPublisher;

	// security
	private final AuthTokenValidator authTokenValidator;
	private final TokenGenerator tokenGenerator;
	private final BlackTokenAuthCommand blackTokenAuthCommand;
	private final ReplaceTokenCacheService replaceWhiteAccessTokenCacheServiceImpl;

	@MemberSecurityChainedTransactional
	public CheckEmailAuthUseCaseResponse execute(CheckEmailAuthUseCaseRequest request) {
		final Long memberId = request.getMemberId();
		final String code = request.getCode();
		final EmailData email = EmailData.builder().email(request.getEmail()).build();
		final String nonce = request.getNonce();
		final String sessionId = request.getSessionId();
		final String accessToken = request.getAccessToken();
		final String refreshToken = request.getRefreshToken();

		authTokenValidator.execute(refreshToken, accessToken, memberId);

		log.debug("Check email auth session. sessionId: {}", sessionId);
		Optional<EmailAuthSession> emailAuthSessionSource = emailAuthDao.findBySessionId(sessionId);
		if (emailAuthSessionSource.isEmpty()) {
			throw new SourceNotFoundException(DBSource.EMAIL_AUTH_SESSION, "SessionId", sessionId);
		}

		MemberSource memberSource = memberSourceQuery.execute(memberId);

		log.debug("Get email auth source. memberId: {}, email: {}, nonce: {}", memberId, email, nonce);
		EmailAuthSource emailAuth = getEmailAuth(memberSource, email, nonce);
		final Long emailAuthId = emailAuth.getId();

		// 이메일 인증 요청한 멤버와 요청한 멤버가 일치하는지 확인
		if (!emailAuth.isMemberId(memberSource.getId())) {
			throw new InvalidRequestException("request member id is not matched", null);
		}
		// 이메일 인증 요청시 발급한 nonce와 요청한 nonce가 일치하는지 확인
		if (!emailAuth.isNonce(nonce)) {
			throw new InvalidRequestException("request nonce is not matched", "nonce");
		}

		log.debug("Get try count. memberId: {}, emailAuthId: {}", memberId, emailAuthId);
		// 이메일 인증을 시도한 횟수를 조회
		TryCountElement tryCount = null;
		try {
			tryCount = getTryCount(memberId, emailAuthId);
		} catch (OverMaxTryCountException e) {
			log.debug("Delete email auth session because tryCount is over max. sessionId: {}", sessionId);
			emailAuthDao.deleteBySessionId(sessionId);
			return CheckEmailAuthUseCaseResponse.builder()
					.authentication(false)
					.tryCount(Long.valueOf(MAX_TRY_COUNT))
					.build();
		}
		assert tryCount != null;

		// 이메일 인증 요청시 발급한 code와 요청한 code가 일치하는지 확인
		if (!emailAuth.isCode(code)) {
			log.debug("Not match code. memberId: {}, emailAuthId: {}", memberId, emailAuthId);
			tryCount = emailAuthLogService.calculateTryCount(NOT_MATCH_CODE, tryCount);
			EmailAuthLogEntity emailAuthLogEntity =
					emailAuthLogService.saveLog(tryCount, memberId, emailAuthId, NOT_MATCH_CODE.getReason());
			log.debug(
					"Save Fail email auth log. memberId: {}, emailAuthId: {}, emailAuthLogId: {}",
					memberId,
					emailAuthId,
					emailAuthLogEntity.getId());
			return CheckEmailAuthUseCaseResponse.builder()
					.authentication(false)
					.tryCount(emailAuthLogEntity.getTryCount())
					.build();
		}
		// 일치하는 이메일을 외부 연락처로 저장
		ExternalContactEntity externalContactEntity =
				ExternalContactEntity.builder()
						.memberId(memberSource.getId())
						.contactType(ContactType.EMAIL)
						.source(email.getEmail())
						.build();
		Long savedExternalContactId = externalContactDao.saveContact(externalContactEntity).getId();
		log.debug(
				"Save external contact. memberId: {}, contactId: {}", memberId, savedExternalContactId);
		tryCount = emailAuthLogService.calculateTryCount(SUCCESS, tryCount);
		EmailAuthLogEntity emailAuthLogEntity =
				emailAuthLogService.saveLog(tryCount, memberId, emailAuthId, SUCCESS.getReason());
		log.debug(
				"Save Success email auth log. memberId: {}, emailAuthId: {}, emailAuthLogId: {}",
				memberId,
				emailAuthId,
				emailAuthLogEntity.getId());
		log.debug("Delete email auth session. sessionId: {}", sessionId);
		emailAuthDao.deleteBySessionId(sessionId);

		log.debug("Get authentication. memberId: {}", memberId);
		Optional<AuthenticationEntity> authenticationSource =
				authenticationDao.findByMemberIdAndDeletedFalse(memberId);
		if (authenticationSource.isEmpty()) {
			throw new SourceNotFoundException(DBSource.AUTHENTICATION, "MemberId", memberId);
		}
		MemberAuthentication memberAuthentication =
				MemberAuthenticationConverter.from(authenticationSource.get());

		MemberContacts memberContacts = memberContactsQuery.execute(memberAuthentication);

		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						memberSource.getId(),
						List.of(Roles.ROLE_USER),
						memberAuthentication.getCertification(),
						memberContacts.getEmail(),
						memberContacts.getGithub());

		blackTokenAuthCommand.execute(accessToken, refreshToken);
		replaceWhiteAccessTokenCacheServiceImpl.execute(
				accessToken, authToken.getAccessToken(), memberAuthentication.getMemberId());

		publishEvent(memberId, memberAuthentication, email);

		return SuccessCheckEmailAuthUseCaseResponse.builder()
				.authentication(true)
				.tryCount(emailAuthLogEntity.getTryCount())
				.accessToken(authToken.getAccessToken())
				.refreshToken(authToken.getRefreshToken())
				.build();
	}

	private EmailAuthSource getEmailAuth(MemberSource memberSource, EmailData email, String nonce) {
		Optional<EmailAuthEntity> emailAuthSource =
				emailAuthDao.findByMemberIdAndEmailAndNonceAndDeletedFalse(
						memberSource.getId(), email, nonce);
		if (emailAuthSource.isEmpty()) {
			throw new SourceNotFoundException(DBSource.EMAIL_AUTH, "MemberId", memberSource.getId());
		}
		return EmailAuthSourceConverter.from(emailAuthSource.get());
	}

	private TryCountElement getTryCount(Long memberId, Long emailAuthId) {
		// 이메일 인증을 실패한 이력이 있는지 조회
		Optional<EmailAuthLogEntity> emailAuthLogSource =
				emailAuthLogDao.findByMemberIdAndEmailAuthIdAndReasonNotAndDeletedFalse(
						memberId, emailAuthId, SUCCESS.getReason());
		int tryCount;
		if (emailAuthLogSource.isEmpty()) {
			// 이메일 인증을 실패한 이력이 없는 경우 tryCount를 0으로 초기화
			tryCount = 0;
			return TryCountElement.builder().tryCount(tryCount).build();
		} else {
			// 이메일 인증을 실패한 이력이 있는 경우 tryCount를 조회하여 초기화
			tryCount = Math.toIntExact(emailAuthLogSource.get().getTryCount());
			if (tryCount >= MAX_TRY_COUNT) {
				throw new OverMaxTryCountException("request try count is over");
			}
			return TryCountElement.builder()
					.tryCount(tryCount)
					.emailAuthLogId(emailAuthLogSource.get().getId())
					.build();
		}
	}

	private void publishEvent(Long memberId, MemberAuthentication authentication, EmailData email) {
		applicationEventPublisher.publishEvent(
				AddEmailEvent.builder()
						.memberId(memberId)
						.memberCertification(authentication.getCertification())
						.memberEmail(email.getEmail())
						.build());
	}
}
