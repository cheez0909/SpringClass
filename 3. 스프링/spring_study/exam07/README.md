hadlerMapping 
-> url과 요청 타입으로 컨트롤러를 찾음

handelerAdapter
-> 스프링 빈에서 해당 컨트롤러를 찾음
-> 실행 결과를 modelandView로 변환해서 리턴

viewResolver
-> 컨트롤러의 실행결과를 보여 줄 view 호출

모든 요청 -> 서블릿(dispatcher)

![img.png](img.png)