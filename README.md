# SpringMVC_HTTP

SpringBoot 기반으로 공부용 입니다.
> 로깅의 필요성과 사용 방법, Spring MVC의 기본 기능인 HTTP 요청, 응답 메시지 처리를 알아보는 '공부용'입니다.
> 인프런의 김영한 님의 강의인 '스프링 MVC 1편'을 보고 정리한 내용입니다.
> https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/dashboard

*Description*

> 로깅, HTTP의 요청, 응답에 대한 Spring의 기본 기능 등을 연습해봅니다.

*Environment*

> IntelLiJ
> 
> Java 11
> 
> Gradle
>
> Jar
>
*Period*

> Start : ***2021/12/10~***
> 
> End : ***~ing***

***2021/12/10***
> * Logging
>
> 운영 시스템에서는 System.out.println()과 같은 시스템 콘솔을 사용해서 출력하지 않고 별도의 Logging Library를 사용해서 출력한다. 로그를 사용하면 별도의 파일, 네트워크 등에 결과를 남길 수 있고, 일정 용량이 넘어가면 다른 파일을 생성하여 확장성있게 유지할 수 있다. 
> 
> Spring Boot Library를 사용하면 spring-boot-starter-logging Library가 함께 포함된다. Library는 Logback, Log4J, Log4J2 등 많이 있는데, 그것을 통합해서 Interface로 제공하는 것이 SLF4J Library이다. 따라서 Interface는 SLF4J고 구현체로 Logback과 같은 Log Library를 선택하면 된다.
> 
> * 로그 선언의 방식
>
> private Logger log = LoggerFactory.getLogger(getClass());
> 
> private static final Logger log = LoggerFactory.getLogger(Xxx.class);
> 
> @Slf4j (Lombok 사용시)
> 
> * 로그 호출 방식
> 
> log.info("hello"), log.info("log username={}", username), log.trace("trace username={}, age={}", username, age)과 같은 방식으로 호출한다.
> 
> 여기서 주의해야할 점이 있다면 log.info("log username=" + username)과 같은 더하기 연산자를 쓰면 안된다는 것이다. info의 처리보다 더하기 연산자가 먼저 실행되어 로그 레벨에 따라 출력이 안되는 경우에도 더하기 연산자의 결과가 메모리를 잡아먹게 된다. 따라서 연산자를 사용하는 것 보다 log.info("log username={}", username)과 같이 Parameter를 넘겨주는 방식으로 해야된다.
> 
> ![image](https://user-images.githubusercontent.com/69206748/145516760-aeb1d393-4a3e-4108-93de-34d2c023d627.png)
>
> * @RestController
> 
> @Controller는 반환 값이 String이면 ViewName으로 인식된다. 따라서 View를 찾고 rendering된다.
>
> @RestController는 반환 값을 HTTP MessageBody에 바로 입력한다. (@RestController를 타고 들어가보면 @ResponseBody가 있다.) 따라서 실행 결과로 ok메시지를 얻을 수 있다.
> 
> * Test
> 
> 로그가 출력되는 포멧을 확인할 수 있다. (시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스 명, 로그 메시지)
>
> LEVEL : TRACE > DEBUG > INFO > WARN > ERROR
> 
> 보통 개발 서버는 debug, 운영 서버는 info를 출력한다.
>
> * 로그 레벨 설정
>
>![image](https://user-images.githubusercontent.com/69206748/145517196-1a524247-d043-4fcf-a49e-7f51bf5388db.png)
>
> * 로그 사용시 장점
> 
> 1. 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양 조정 가능
> 2. 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영 서버에서는 출력하지 않게 상황에 따라 조절 가능
> 3. 시스템 아웃 콘솔에만 출력하는 것이 아니라 파일, 네트워크 등 별도의 위치에 남길 수 있다.
> 4. 성능도 System.out보다 좋다.(내부 버퍼링, 멀티 쓰레드 등) -> 수 십배의 속도 차이가 날 수 있다.
>
>
> ![image](https://user-images.githubusercontent.com/69206748/145672768-48d2e378-a889-4cfb-8790-d5f4b8c119b4.png)
>
> * PathVariable(경로 변수)사용
>
> 만일 @PathVariable의 이름과 Parameter의 이름이 같으면 생략할 수 있다.
>
> 최근 HTTP API는 다음과 같이 리소스 경로에 식별자를 넣는 스타일을 선호한다.
>
> /mapping/{userId}/orders/{orderId}
> 
> * 요청 매핑 - API 예시
> 회원 관리를 HTTP API로 만든다고 하면 다음과 같이 만들 수 있다.
>
> 1. 회원 목록 조회 : GET /users
> 2. 회원 등록 : POST /users
> 3. 회원 조회 : GET /users/{userId}
> 4. 회원 수정 : PATCH /users/{userId}
> 5. 회원 삭제 : DELETE /users/{userId}
> 
> 회원 조회 Controller를 만든다면 다음과 같이 만들 수 있다.
> (Class Level에 @RequestMapping("/mapping/users") 선언하여 경로 통합)
> 
> ![image](https://user-images.githubusercontent.com/69206748/145672959-3f76887e-a127-4ad9-b2db-c397d67181e8.png)
>
>
***2021/12/11***
> * HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
>
> 클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 3가지 방법을 사용한다.
> 
> * GET - 쿼리 파라미터
> 1. /url?username=hello&age=20
> 2. Message Body 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달한다.
> 3. ex) 검색, 필터, 페이징에서 많이 사용하는 방식이다.
> 
> * POST - HTML Form
> 1. content-type:application/x-www-form-urlencoded
> 2. Message Body에 쿼리 파라미터 형식으로 전달한다. username=hello&age=20
> 3. ex) 회원 가입, 상품 주문에서 많이 사용하는 방식이다.
>
> * HTTP Message Body에 데이터를 직접 담아서 요청
> 1. HTTP API에서 주로 사용(JSON, XML, TEXT)
> 2. 데이터 형식은 주롤 JSON을 사용한다.
> 3. POST, PUT, PATCH를 사용할 수 있다.
>
> * 요청 파라미터 - 쿼리 파라미터, HTML Form
>
> HttpServletRequest의 request.getParameter()를 사용하면 GET-쿼리 파라미터, POST, HTML Form 전송에서 Parameter를 뽑아낼 수 있다.
>
> ![image](https://user-images.githubusercontent.com/69206748/145712278-b5ac1d2b-f34f-4dc7-81fd-ad5219ee561b.png)
>
> * HTTP 요청 파라미터 - @RequestParam
>
> 스프링이 제공하는 @RequestParam을 이용하면 파라미터 이름으로 바인딩을 해준다.
>
> ![image](https://user-images.githubusercontent.com/69206748/145714833-e77988a2-e8f4-4a01-a08a-79c27dd17784.png)
>
>
***2021/12/12***
> * HTTP 요청 파라미터 - @ModelAttribute
> 
> 실제 개발을 하면 요청 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주어야 한다.
> 
> ![image](https://user-images.githubusercontent.com/69206748/145755801-60a8a87b-e1f1-4d98-b520-62fab35382d8.png)
>
> 스프링은 다음과 같은 과정을 자동화해주는 @ModelAttribute 기능을 제공한다.
>
> 우선 요청 파라미터를 바인딩 받을 객체 HelloData를 생성하자.
> 
> ![image](https://user-images.githubusercontent.com/69206748/145755917-51fac123-bb65-433e-aab9-230a6f56deb2.png)
>
> ![image](https://user-images.githubusercontent.com/69206748/145756171-fce961b8-2a9e-4131-af3b-c1b308752c31.png)
>
> @ModelAttribute가 있으면 다음과 같이 실행된다.
> 1. HelloData 객체를 생성한다.
> 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출하여 파라미터 값을 바인딩한다.
>
>
> 물론 @ModelAttribute를 생략할 수 있지만 @RequestParam과 혼란을 야기할 수 있기 때문에 추천하는 방법은 아니다.
>
> 스프링은 생략시 다음과 같은 규칙을 적용한다고 알려져 있다.
>
> 1. String, int, Integer 같은 단순 타입 = @RequestParam
> 2. 나머지 = @ModelAttribute(argument resolver로 지정해둔 타입 외)
>
***2021/12/13***
> * HTTP 요청 파라미터 - 단순 텍스트
> 
> HTTP message body에 데이터를 직접 담아서 요청
> 
> 1. HTTP API에서 주로 사용, JSON, XML, TEXT 등
> 2. 데이터 형식은 주로 JSON 사용
> 3. POST, PUT, PATCH
>
> 이 때 주의해야할 점은 요청 파라미터와는 다르게, HTTP message body를 통해 데이터가 직접 넘어오는 경우는 @RequestParam, @ModelAttribute를 사용할 수 없다. 물론 HTML Form형식으로 넘어오는 경우는 message body에 요청 파라미터가 있기 때문에 사용 가능하다.
> 
> 
