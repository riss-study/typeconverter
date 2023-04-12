package dev.riss.typeconverter.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class FormatterController {

    // form 의 데이터가 아래 Form class 에 정의된 Formatter (annotation) 에 의해
    // 10000 -> "10,000"
    // 2023-04-10T16:39:21 -> "2023년 04월 10일 16시 39분 21초" 로 convert 가 잘됨
    @GetMapping("/formatter/edit")
    public String formatterForm (Model model) {
        Form form=new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", form);
        return "formatter-form";
    }

    // 마찬가지로 아래의 Form 에 Formatter 가 적용
    // "10,000"
    // "2023년 04월 10일 16시 39분 21초" 가 문자로 들어오는데
    // Form 객체로 데이터를 바인딩하려고 봤더니, 포맷터가 있으므로 각각
    // 10000 (숫자 타입 Integer 객체), 2023-04-10T16:39:21 (LocalDateTime 객체) 로 변환해줌
    @PostMapping("/formatter/edit")
    public String formatterEdit (@ModelAttribute Form form) {
        return "formatter-view";
    }

    // ** 주의: 메시지 컨버터(HttpMessageConverter) 에는 컨버전 서비스 적용 x
    // HttpMessageConverter 의 기능은 Http message body <-> object(객체) 변환
    // 만약 JSON <-> Object 메시지 컨버터는 내부에서 Jackson 같은 라이브러리 사용
    // 그러므로 해당 라이브러리가 제공하는 포맷터 혹은 컨버터를 사용해야 함 (ex. @JsonFormat 같은거)
    // ex. JSON => 커스터마이징 필요시, 타프로젝트에서 스프링 빈으로 Jackson2ObjectMapperBuilderCustomizer 를 따로 등록하여
    // 직접 포맷터 지정해준 serializer(ex. LocalDateSerializer), deserializer(ex. LocalDateTimeDeserializer) 를
    // Jackson2ObjectMapperBuilder 객체에 등록해서
    // @JsonFormat 으로 사용한 경험이 있음 (ex. LocalDate 형식 String -> LocalDateTime 포맷 변환 시)
    @Data
    static class Form {
        @NumberFormat(pattern = "###,###")
        private Integer number;
        @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초")
        private LocalDateTime localDateTime;
    }
}
