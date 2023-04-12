package dev.riss.typeconverter.formatter;

import dev.riss.typeconverter.converter.IpPortToStringConverter;
import dev.riss.typeconverter.converter.StringToIpPortConverter;
import dev.riss.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService convService = new DefaultFormattingConversionService();

        // 컨버터 등록
        convService.addConverter(new StringToIpPortConverter());
        convService.addConverter(new IpPortToStringConverter());

        // 포맷터 등록
        convService.addFormatter(new MyNumberFormatter());

        // 컨버터 사용
        IpPort ipPort = convService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        // 포맷터 사용
        assertThat(convService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(convService.convert("1,000", Long.class)).isEqualTo(1000L);
        
        // 스프링부트는 DefaultFormattingConversionService 를 상속받은 WebConversionService 를 내부적으로 사용
        // 즉, 통합한 저 WebConversionService 객체 안에 우리가 등록한 컨버터, 포맷터가 다 들어있음
    }
}
