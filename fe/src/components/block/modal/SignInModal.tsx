import Text from '@components/atom/Text';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import CustomInput from '@components/atom/Input/CustomInput';
import { useState } from 'react';
import useCheckIdDuplicate from '@hooks/query/member/useCheckIdDuplicate';
import useSignUp from '@hooks/query/member/useSignUp';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 29.25rem;
  height: 33rem;
  padding: 0.25rem 16px;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 10px;
  padding-right: 55px;
  padding-left: 3.4375rem;
  position: relative;
`;

const FlexBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  flex-wrap: wrap;
  gap: 0.5rem;
`;

const IdBox = styled.div`
  display: flex;
  width: 22.375rem;
  justify-content: space-between;
  align-items: center;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  bottom: 3.5rem;
`;

const SignInModal = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [isDuplicate, setIsDuplicate] = useState(false);
  const [isButtonDisabled, setIsButtonDisabled] = useState(false);

  const { data: duplicateData } = useCheckIdDuplicate({ certification: email });

  const handleIdDuplicate = () => {
    if (duplicateData?.data.data.duplication) {
      // 중복일시 버튼 비활성화
      setIsButtonDisabled(true);
      console.log(isDuplicate);
      alert('중복임');
    } else {
      setIsButtonDisabled(false);
    }
  };

  const { mutate: signUp } = useSignUp();
  const validateForm = () => {
    if (email.trim() === '') {
      alert('아이디 입력하셈');
    } else if (password.trim() === '') {
      alert('비밀번호 입력하셈');
    } else if (passwordConfirm.trim() === '') {
      alert('비밀번호확인 입력하셈');
    } else if (
      password.trim().length < 8 ||
      !/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(password)
    ) {
      alert('비밀번호는 8글자 이상, 영문, 숫자, 특수문자 사용');
    } else if (!(password === passwordConfirm)) {
      alert('확인이랑 비밀번호가 다름');
    } else {
      // 모든 조건을 통과하면 로그인 처리 또는 다른 작업 수행
      signUp({
        certification: email,
        password: password,
      });
      alert('회원가입 성공');
      setIsDuplicate(false);
    }
  };
  return (
    <div>
      <LoginBox>
        <Text
          fontSize="xxl"
          fontWeight="bold"
          style={{
            marginTop: '3rem',
            marginBottom: '1.25rem',
          }}
        >
          회원가입
        </Text>
        <Text
          fontSize="lg"
          style={{
            marginBottom: '0.5rem',
          }}
        >
          아이디
        </Text>
        <IdBox>
          <CustomInput
            type="email"
            placeholder="이름 입력"
            width={15.5}
            height={3}
            padding={1}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
          />
          <DefaultButton
            onClick={() => {
              handleIdDuplicate();
            }}
            width={5.5}
            height={3.25}
            padding={1}
          >
            중복 확인
          </DefaultButton>
          {/* <DoubleCheckButton /> */}
        </IdBox>
        <Text
          fontSize="lg"
          style={{
            marginTop: '1.5rem',
            marginBottom: '0.5rem',
          }}
        >
          비밀번호
        </Text>

        <FlexBox>
          <CustomInput
            type="password"
            width={22}
            height={3}
            padding={1}
            placeholder="비밀번호 입력"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
            // value=''
          />
          <CustomInput
            type="password"
            width={22}
            height={3}
            padding={1}
            placeholder="비밀번호 확인"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setPasswordConfirm(e.target.value)
            }
            // value=''
          />
        </FlexBox>
        <ButtonBox>
          <DefaultButton
            width={22.375}
            height={3}
            padding={1}
            onClick={validateForm}
            disabled={isDuplicate}
          >
            회원가입
          </DefaultButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default SignInModal;
