## 📕 Maven으로 파일 생성하기
___


### 📩 Maven 다운로드
> https://maven.apache.org/download.cgi

1. `apache-maven-3.9.5-bin.zip` 파일 다운로드
2. 알집 해제 후 내 프로젝트 디렉토리 내부로 붙여넣기
3. `bin`폴더 경로 복사 후 환경 변수 편집
   👉 `D:\dani\apache-maven-3.9.5\bin` 추가
   ![](https://velog.velcdn.com/images/dani0817/post/547b00fa-d500-428b-991e-3d53f88c7903/image.png)

<br>
<br>

### 💻 1. cmd 창으로 생성하기
___
#### 📩 mvn -v
![](https://velog.velcdn.com/images/dani0817/post/8d26ea7b-561e-448b-a2f0-bd9ddf09f33c/image.png)
> 현재 버전 확인할 수 있음

<br>

#### 📩 mvn archetype:generate
![](https://velog.velcdn.com/images/dani0817/post/06d50f58-4554-4f27-a233-1d53cdd82007/image.png)
![](https://velog.velcdn.com/images/dani0817/post/b3e4e3a9-5b0b-4f57-bc35-4db0287a267a/image.png)

> `groupId`와 `artifactId`를 설정 후 `C:\Users\admin`에 `artifactId`으로 폴더가 생성된다. 해당 폴더를 내 프로젝트 내부로 이동해서 사용할 수 있다.

<br>

#### 💾 Maven 탭
![](https://velog.velcdn.com/images/dani0817/post/4ee2a4cf-fcf9-4ba6-b5d0-5e38e1fb1adf/image.png)

1. 새로고침
2. 자동 다운로드
3. 명령어

> ✅ `mvn package ` : `target` 폴더 내부에 `jar` 파일 생성

<br>


https://velog.io/@dani0817/Maven-maven%EC%9C%BC%EB%A1%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0