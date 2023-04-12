package dev.riss.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {   // Number=> int, long 등의 숫자 타입의 부모 클래스
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("[MyNumberFormatter::parse] text={}, locale={}", text, locale);
        // "1,000" -> 1000
        NumberFormat format = NumberFormat.getInstance(locale);     // locale 정보를 활용하여 나라 벌로 다른 숫자 포맷을 만들어줌
        return format.parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        log.info("[MyNumberFormatter::print] object={}, locale={}", object, locale);
        // 1000 -> "1,000"
        return NumberFormat.getInstance(locale).format(object);
    }
}
