package dev.riss.typeconverter.converter;

import dev.riss.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionServiceTest {

    @Test
    void conversionService () {
        // 등록 (실제로는 이 부분을 스프링 빈으로 등록해서 사용해야할 것 주의)
        DefaultConversionService convService = new DefaultConversionService();
        convService.addConverter(new StringToIntegerConverter());
        convService.addConverter(new IntegerToStringConverter());
        convService.addConverter(new StringToIpPortConverter());
        convService.addConverter(new IpPortToStringConverter());

        String strIpPort = "127.0.0.1:8080";
        IpPort ipPort = new IpPort("127.0.0.1", 8080);

        // 사용
        assertThat(convService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(convService.convert(10, String.class)).isEqualTo("10");

        assertThat(convService.convert(strIpPort, IpPort.class)).isEqualTo(ipPort);
        assertThat(convService.convert(ipPort, String.class)).isEqualTo(strIpPort);

    }
}
