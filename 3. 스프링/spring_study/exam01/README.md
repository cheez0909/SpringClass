[1. 예외 처리](#1-예외-처리runtimeexception-vs-compileexception)



### spring-context
___
#### 💾 `build.gradle` 의존성 추가
`implementation 'org.springframework:spring-context:6.1.1'`
![img.png](img.png)
> 전부 추가 됨

<br>
<br>

### 1. 예외 처리(`RuntimeException` vs `CompileException`)
#### 📂 java > main > 💾 `Main04`, 💾 `Main05`
___
### 1-1. `CompileException` (💾 `Main04`)
 - 클래스 파일이 생성되지 않고, 컴파일 시점에 예외를 체크하고 컴파일 하지 않는다.
> 따라서 반드시 예외 처리가 필요하다.(엄격한 예외)

<br>

### 1-2. `RuntimeException`  (💾 `Main05`)
- 실행중에 예외를 체크하므로 예외가 발생하더라도 `.class` 파일이 생성된다.
- 서비스 중단을 막기 위해서 적절한 예외 처리가 필요하다.
> 유연한 예외, 예외가 발생 하더라도 실행은 됨

<br>
<br>

### 2-1. 예외 처리 구문
```
try{
    예외가 발생할 가능성이 있는 코드
} catch(예외 객체){
    예외에 대한 후속 처리
}
```