package dev.riss.typeconverter.converter;

import dev.riss.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IpPortConverterTest {

    @Test
    void stringToIpPort () {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        IpPort expect = new IpPort("127.0.0.1", 8080);

        assertThat(result).isEqualTo(expect);
        // 엄연히 다른 참조값을 가진 객체지만, EqualsAndHashCode 를 구현(애노테이션)했기 때문에
        // 이렇게 안의 내용 값으로 비교 가능
    }

    @Test
    void ipPortToString () {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }
}
