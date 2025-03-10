# Member EVENT

## 발행

### 회원가입 이벤트

회원가입이 완료된 이후 발행됩니다.

#### 내역

| 이름                   | 타입            | 설명                |
|----------------------|---------------|-------------------|
| member_id            | Long          | 회원 가입된 멤버 아이디     |
| member_certification | String        | 회원 가입된 멤버 증명(아이디) |
| event_at             | LocalDateTime | 회원가입 이벤트 발행 일자    |

### 이메일 등록 이벤트

이메일이 등록된 이후 발행됩니다.

#### 내역

| 이름                   | 타입            | 설명                   |
|----------------------|---------------|----------------------|
| member_id            | Long          | 이메일이 등록된 멤버 아이디      |
| member_certification | String        | 이메일이 등록된 멤버 증명(아이디)  |
| member_email         | String        | 이메일                  |
| event_at             | LocalDateTime | 이메일 등록 이벤트 이벤트 발행 일자 |

### 회원정보 수정 이벤트

회원정보가 수정된 이후 발행됩니다.

#### 내역

| 이름                   | 타입            | 설명                    |
|----------------------|---------------|-----------------------|
| member_id            | Long          | 회원정보가 수정된 멤버 아이디      |
| member_certification | String        | 회원정보가 수정된 멤버 증명(아이디)  |
| event_at             | LocalDateTime | 회원정보 수정 이벤트 이벤트 발행 일자 |

### 회원 탈퇴 이벤트

회원 탈퇴가 완료된 이후 발행됩니다.

#### 내역

| 이름           | 타입            | 설명                  |
|--------------|---------------|---------------------|
| member_id    | Long          | 회원 탈퇴된 멤버 아이디       |
| published_at | LocalDateTime | 회원 탈퇴 이벤트 이벤트 발행 일자 |

### 로그인 이벤트

로그인이 완료된 이후 발행됩니다.

#### 내역

| 이름           | 타입            | 설명                |
|--------------|---------------|-------------------|
| member_id    | Long          | 로그인된 멤버 아이디       |
| published_at | LocalDateTime | 로그인 이벤트 이벤트 발행 일자 |

### 로그아웃 이벤트

로그아웃이 완료된 이후 발행됩니다.

#### 내역

| 이름           | 타입            | 설명                 |
|--------------|---------------|--------------------|
| member_id    | Long          | 로그아웃된 멤버 아이디       |
| published_at | LocalDateTime | 로그아웃 이벤트 이벤트 발행 일자 |


### 토큰 탈취 이벤트

토큰이 탈취된 이후 발행됩니다.

#### 내역

| 이름           | 타입            | 설명              |
|--------------|---------------|-----------------|
| member_id    | Long          | 토큰이 탈취된 멤버 아이디  |
| ip           | String        | 토큰 탈취를 시도한 ip   |
| published_at | LocalDateTime | 토큰 탈취 이벤트 발행 일자 |

### 이메일 인증 탈취 이벤트

이메일 인증 탈취된 이후 발행됩니다.

#### 내역

| 이름           | 타입            | 설명                  |
|--------------|---------------|---------------------|
| member_id    | Long          | 이메일 인증 탈취된 멤버 아이디   |
| ip           | String        | 이메일 인증 탈취를 시도한 ip   |
| published_at | LocalDateTime | 이메일 인증 탈취 이벤트 발행 일자 |
