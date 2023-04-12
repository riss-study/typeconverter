package dev.riss.typeconverter;

import dev.riss.typeconverter.converter.IntegerToStringConverter;
import dev.riss.typeconverter.converter.IpPortToStringConverter;
import dev.riss.typeconverter.converter.StringToIntegerConverter;
import dev.riss.typeconverter.converter.StringToIpPortConverter;
import dev.riss.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 타입 컨버터 스프링 빈에 등록
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 주석 처리 (우선 순위 때문)
        // 컨버터 -> 포맷터 순으로 우선순위가 생김 
        // => MyNumberFormatter 와 마찬가지로 숫자 <-> 문자 바꾸는 거기 때문에
        // 이 친구를 살리면 컨버터가 우선순위를 가져서 이 친구가 적용되어 아래의 포맷터가 동작하지 않음
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
