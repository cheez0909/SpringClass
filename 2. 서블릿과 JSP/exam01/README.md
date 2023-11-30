### 깃 연습해보기
___

### 📩 git log --oneline
![](https://velog.velcdn.com/images/dani0817/post/44e417e0-ac75-4ce0-a44a-8df183168c13/image.png)

> log를 한 줄로 보여줌

<br>
<br>



### 📩 git branch -M master

![](https://velog.velcdn.com/images/dani0817/post/52a9c313-fefb-4eb4-a4fc-500ed195088a/image.png)

> 메인 이름을 master로 변경

<br>
<br>

### 📩 git branch board

![](https://velog.velcdn.com/images/dani0817/post/8e053139-280b-40ee-81e0-d97db577d9b3/image.png)

> board 라는 이름의 브랜치 생성

<br>
<br>

### 📩 git checkout board
![](https://velog.velcdn.com/images/dani0817/post/7b5fe810-802d-43c3-9f0a-e1169bdaf9ad/image.png)

> 브랜치를 board로 이동

<br>
<br>

### 📩 git log --oneline

![](https://velog.velcdn.com/images/dani0817/post/4e770687-f608-4914-bbd2-2fc6eee94c31/image.png)

> board 브랜치에서 생성한 작업도 표시된다.

<br>
<br>

### 📩 git checkout master
### 📩  git log

![](https://velog.velcdn.com/images/dani0817/post/8c75f5fa-4ce7-4d33-94b9-87b566ba9891/image.png)

> 마스터 브랜치로 이동 후 호그를 확인해보면 마스터에서 작업한 내용만 나온다. 즉, 작업을 독립적으로 할 수 있다.


<br>
<br>

### 📩 git checkout board
### 📩 git branch
![](https://velog.velcdn.com/images/dani0817/post/58edf732-b7ac-43e5-a9fe-19775c77b440/image.png)

> 브랜치 위치를 board로 이동한 후 브랜치 위치를 확인해보자!

<br>
<br>

### 📩 git checkout master
### 📩 git merge board
![](https://velog.velcdn.com/images/dani0817/post/40aa9549-c3b9-4ee4-a471-68f161367c08/image.png)

> merge 시 마스터로 이동 필요함

<br>
<br>

### 📩 git log --oneline

![](https://velog.velcdn.com/images/dani0817/post/2987c534-a962-4b44-97e3-0aacf9c09916/image.png)

> merge된 상태를 확인 할 수 있다.

<br>
<br>

### 📩 git push origin master


> 원격 레파지토리에 push 해준다

<br>

> #### 💡 CMD창 명령어
✅ `cls` : 화면 클리어<br>
✅ `:D` : D드라이브로 이동<br>
✅ `dir/w` : 디렉토리 내부 파일 확인<br>




<br>
<br>

### 🔍 git pull vs git clone 차이
- git pull : remote 설정을 해주지 않음(초기 다운로드에 사용)
- git clone : remote 설정을 자동으로 해줌(리모트 설정이 이미 되어있을 때 업데이트 사항 등을 다운로드)


<br>
<br>

### 💻 새로운 디렉토리에서 작업해보기
#### 📩 git clone 레파지토리주소 .
#### 📩 git remote -v
#### 📩 git branch
![](https://velog.velcdn.com/images/dani0817/post/d1a8e183-78aa-4ba1-a9d7-6974006d33e5/image.png)

> 클론으로 초기 설정을 해준 후 브랜치를 보면 master만 나와있다 why? 기존 디렉토리에서 board 브랜치를 push 해주지 않았다. 기존 디렉토리로 돌아가 git push origin board 해주자!

<br>
<br>

#### 📩 git pull origin board
#### 📩 git branch
#### 📩 git checkout board![](https://velog.velcdn.com/images/dani0817/post/07b994d7-b9ff-435f-9054-16f1f630b41a/image.png)

#### 📩 git branch![](https://velog.velcdn.com/images/dani0817/post/f7ac9a1b-72b7-412a-8b4d-0e95c0b7fcda/image.png)


![](https://velog.velcdn.com/images/dani0817/post/b18bc537-6940-438b-ac9c-25fee9248249/image.png)

> `master`의 merge를 막음
> 깃의 `pull request`를 통해서 `merge` 할 수 있음