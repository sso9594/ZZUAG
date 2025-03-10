package com.zzaug.member.domain.external.service.auth;

import com.zzaug.member.domain.external.dao.log.EmailAuthLogDao;
import com.zzaug.member.entity.log.EmailAuthLogEntity;
import com.zzaug.member.persistence.support.transaction.UseCaseTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthLogCommandImpl implements EmailAuthLogCommand {

	private final EmailAuthLogDao emailAuthLogDao;

	@Override
	@UseCaseTransactional
	public EmailAuthLogEntity execute(
			Long id, Long memberId, Long emailAuthId, String reason, Long tryCount) {
		EmailAuthLogEntity emailAuthLogEntity =
				EmailAuthLogEntity.builder()
						.id(id)
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.reason(reason)
						.tryCount(tryCount)
						.build();
		return emailAuthLogDao.saveEmailAuthLog(emailAuthLogEntity);
	}

	@Override
	@UseCaseTransactional
	public EmailAuthLogEntity execute(Long memberId, Long emailAuthId, String reason, Long tryCount) {
		EmailAuthLogEntity emailAuthLogEntity =
				EmailAuthLogEntity.builder()
						.memberId(memberId)
						.emailAuthId(emailAuthId)
						.reason(reason)
						.tryCount(tryCount)
						.build();
		return emailAuthLogDao.saveEmailAuthLog(emailAuthLogEntity);
	}
}
