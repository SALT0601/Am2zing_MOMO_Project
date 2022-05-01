# Am2zing_MOMO_Project

멀티캠퍼스에서 진행한 지능형 웹서비스 풀스택 과정 7회차 2조의 팀 프로젝트 입니다.

## 팀 소개

- 한찬희 (팀리더)
- 이민혁
- 이유진
- 유하영
- 신주희

# SpringBoot_Web_Project

Spring Boot의 MVC패턴을 기반으로 만든 MBTI별 장소공유 게시판 입니다. 카카오 지도 API를 사용해 같은 MBTI 사람들과 장소를 공유해 공감대를 형성하면 어떨까 하는 생각으로 만들었습니다.

# 사용기술

- Spring Boot 2.5.2
- Spring Security
- JPA
- Querydsl
- MySQL
- JQuery
- Thymeleaf
- Kakao Map API

# 기능설명

- ## 회원가입 및 로그인

  - 회원가입 진행시 이메일, 패스워드, 닉네임, 생년월일 을 입력합니다.
  - 아이디는 이메일 형식이며 중복체크를 통해 중복 이메일 가입을 방지하고 있습니다.
  - 패스워드는 패스워드 확인을 이용해 체크하며 `BCryptPasswordEncoder`를 이용해 암호화 하여 DB에 저장하였습니다.
  - 닉네임은 웹페이지 이용시 주로 사용되므로 닉네임 역시 중복체크를 통해 중복 닉네임 가입을 방지하고 있습니다.

- ## 회원

  - 회원은 `Member`라는 Role을 소유하고 있습니다.
  - 회원은 공지사항을 제외한 게시물을 작성하고 댓글 작성이 가능합니다.
  - 회원은 MyPage에서 본인이 작성한 게시물 및 댓글을 관리 할 수 있습니다.
  - 회원은 개인정보관리 페이지에서 패스워드 및 회원정보를 수정 할 수 있습니다. 이때 패스워드 변경 시 기존 패스워드를 한번 더 체크합니다.
  - 회원정보 수정 시 닉네임은 중복체크를 통해 변경이 가능합니다.

<details>
    <summary>회원가입</summary>

![join](https://user-images.githubusercontent.com/79136087/166146202-d132214b-3455-4551-acc2-b3be5d821751.gif)

</details>

<details>
  <summary>로그인</summary>

![login](https://user-images.githubusercontent.com/79136087/166146231-99ee7bc4-9b1a-466a-af86-59519489cea5.gif)

</details>

<details>
  <summary>마이페이지</summary>

![마이페이지](https://user-images.githubusercontent.com/79136087/166146252-61ba1a4b-af4b-428a-addd-bf2f82cb6b6f.gif)

</details>

- ## 관리자

- Spring Security를 이용하여 로그인 과정 시 로그인을 완료하면 회원의 Role이 `Admin`과`Member` 로 구분이 되어있습니다.
- 관리자는 모든 게시물 및 댓글들의 삭제 권한이 주어집니다.
- 관리자는 모든 회원들의 정보를 확인할 수 있고 일부 회원의 정보를 편집 수정 할 수 있습니다.
<details>
    <summary>회원 관리</summary>

![관리자 회원 관리](https://user-images.githubusercontent.com/79136087/166146734-458ec6a8-01d7-4936-b4f5-a4b5f8290b24.gif)

</details>
<details>
    <summary>게시글 관리</summary>

![관리자 게시글 삭제](https://user-images.githubusercontent.com/79136087/166146731-bccf5ff9-0051-4027-ac78-4d424cc81e14.gif)

</details>
<details>
    <summary>댓글 관리</summary>

![관리자 댓글 관리](https://user-images.githubusercontent.com/79136087/166146732-cc09601b-ce27-44d9-b0db-f0a71755ce1a.gif)

</details>

- ## 게시물 작성

  - 모든 회원은 게시물을 작성 할 수 있습니다. 게시물 작성 시 `게시글 제목`을 먼저 입력합니다.
  - 제목을 입력하면 게시글 작성 페이지로 이동해 장소를 추가합니다.
  - 장소 선택하기 클릭 시 `Kakao Map API`를 이용하여 모달찰이 생성 됩니다. 원하는 장소를 검색 후 선택하기를 누르면 장소가 선택됩니다.
  - 장소를 선택한 뒤 사진을 추가할 수 있고 장소에 대한 코멘트를 작성해 장소 추가하기를 누르면 저장된 장소가 추가됩니다.
  - 이후 글쓰기 버튼을 클릭시 게시글이 등록됩니다.

<details>
  <summary>글쓰기</summary>

![글쓰기](https://user-images.githubusercontent.com/79136087/166146268-1ac79e89-9cdb-426a-addf-f0bbf54bec8a.gif)

</details>
<details>
  <summary>댓글</summary>

![댓글](https://user-images.githubusercontent.com/79136087/166146403-1b26d8db-c144-4ea0-9edc-c13d0da02c21.gif)

</details>

- ## 게시물 리스트 & 상세 보기

  - 게시판으로 이동하면 게시글 리스트를 확인 할 수 있습니다.
  - 게시글을 클릭하면 `Kakao Map API`를 사용한 게시글을 볼 수 있습니다. 지도 위에 게시글 제목과 장소, 사진, 코멘트를 확인 할 수있습니다.
  - 회원 로그인 된 상태라면 게시글을 북마크에 저장할 수 있습니다.
  - 댓글 보기 버튼을 클릭하면 댓글 창으로 전환되며 댓글을 작성할 수 있습니다.

<details>
    <summary>북마크</summary>

![북마크](https://user-images.githubusercontent.com/79136087/166146431-d714970b-c03d-4844-8c23-75df3f1c7092.gif)

</details>

- ## 지도에 게시물 표시
  - `Kakao Map API`를 이용하여 지도 위에 저장된 장소의 마커가 보이게 했습니다.
