package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 안을 보면 @ResponseBody가 있다. 따라서 String 그대로 반환이 된다.
 * -> REST API만들 때 필요한 Annotation이다.
 *
 * @Controller의 반환 값이 String이면 viewName이 반환되기 때문에 ViewResolver를 찾는 동작을 한다.
 */
@Slf4j
@RestController
public class LogTestController {

    //@Slf4j가 자동으로 코드 넣어준다.
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);//디버그 용, 개발 서버에서 보는 log
        log.info("info log={}", name);//운영 시스템, 비즈니스 쪽에서 보는 log
        log.warn("warn log={}", name);//경고
        log.error("error log={}", name);//에러

        return "ok";
    }
}
