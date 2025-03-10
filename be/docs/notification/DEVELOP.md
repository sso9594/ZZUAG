# Notification API

## 목차

| 정보                 | DEVELOP 바로가기               | API 바로가기                           |
|--------------------|----------------------------|------------------------------------|
| 다른 멤버에게 리뷰를 요청합니다. | [바로가기](#다른-멤버에게-리뷰를-요청합니다) | [바로가기](./API.md#다른-멤버에게-리뷰를-요청합니다) |
| 알림 목록을 조회합니다.      | [바로기기](#알림-목록을-조회합니다)      | [바로기기](./API.md#알림-목록을-조회합니다)      |

## 다른 멤버에게 리뷰를 요청합니다.

### 설계

1. 아이디를 통해 요청자의 정보를 조회합니다.
2. 요청자의 정보를 통해 요청자가 질문글의 작성자인지 확인합니다.
3. 요청자가 질문글의 작성자라면, 요청자의 아이디를 통해 요청받은 멤버의 정보를 조회합니다.
4. 요청 받은 멤버가 이미 해당 글의 리뷰를 요청받은 멤버인지 확인합니다.
5. 요청 받은 멤버에게 리뷰를 요청합니다.

### 예외

| 관련 번호 | 처리                           |
|-------|------------------------------|
| 1,3   | 멤버가 존재하지 않으면 예외를 발생시킨다.      |
| 2     | 멤버가 작성한 질문글이 아니라면 예외를 발생시킨다. |
| 4     | 설정할 리뷰 요청 정책에 따라 예외를 발생시킨다.  |

### 쿼리

| 관련 번호 | 쿼리                                         |
|-------|--------------------------------------------|
| 1     | `id` 를 통해 멤버를 조회합니다.                       |
| 2     | `question_id` 을 통해 질문글을 조회합니다.             |
| 3     | `request_member_id` 를 통해 리뷰 요청할 멤버를 조회합니다. |

### 특이 사항

- 아직은 정해지지 않은 리뷰 요청 정책에 따라 예외를 발생시킵니다.
- 질문글 정보를 위해 질문 서비스를 호출합니다. (2번)
- 멤버 조회를 위해 멤버 서비스를 호출합니다. (3번)

## 알림 목록을 조회합니다.

### 설계

1. 아이디를 통해 멤버 정보를 조회합니다.
2. 멤버의 정보를 통해 알림 목록을 조회합니다.

### 예외

| 관련 번호 | 처리                           |
|-------|------------------------------|
| 1,3   | 멤버가 존재하지 않으면 예외를 발생시킨다.      |
| 2     | 멤버가 작성한 질문글이 아니라면 예외를 발생시킨다. |
| 4     | 설정할 리뷰 요청 정책에 따라 예외를 발생시킨다.  |

### 쿼리

| 관련 번호 | 쿼리                       |
|-------|--------------------------|
| 1     | `id` 를 통해 멤버를 조회합니다.     |
| 2     | 멤버 아이디를 통해 알림 목록을 조회합니다. |
