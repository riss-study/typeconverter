package dev.riss.typeconverter.controller;

import dev.riss.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");     // 문자 타입 조회
        Integer intValue = Integer.valueOf(data);            // 숫자 타입으로 변경
        log.info("intValue = {}", intValue);

        return "ok";
    }

    @GetMapping("/hello-v2")    //"10,000" -> 10000 으로 로그 출력 (MyNumberFormatter 적용)
    public String helloV2 (@RequestParam Integer data) {        // @RequestParam 을 사용하면 (@ModelAttribute, @PathVariable 도 해줌)
                                                            // 스프링이 자동으로 타입을 문자 -> 숫자 타입으로 변환해줌
        log.info("data = {}", data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String convertIpPort (@RequestParam IpPort data) {
        // @RequestParam 을 처리하는 ArgumentResolver 인 RequestParamMethodArgumentResolver 에서
        // ConversionService 를 사용해서 타입 변환
        log.info("data = {}", data);
        log.info("ipPort IP = {}", data.getIp());
        log.info("ipPort PORT = {}", data.getPort());
        return "ok";
    }
}
