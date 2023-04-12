package dev.riss.typeconverter.type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//"127.0.0.1:8080" => ip, port 로 변환을 기대
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class IpPort {

    private String ip;
    private int port;
}
