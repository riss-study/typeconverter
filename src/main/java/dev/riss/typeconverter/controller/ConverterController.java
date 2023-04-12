package dev.riss.typeconverter.controller;

import dev.riss.typeconverter.type.IpPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm (Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form=new Form(ipPort);
        model.addAttribute("form", form);
        return "converter-form";
    }

    @PostMapping("/converter/edit")
    public String converterEdit (@ModelAttribute Form form, Model model) {  //127.0.0.1:8080 문자가 넘어오지만,
        // @ModelAttribute 가 붙어 있음 => Form 안에 IpPort 객체가 있으므로 IpPortToStringConverter 가 호출되어 자동 변환해준다
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "converter-view";
    }

    @Data
    @AllArgsConstructor
    static class Form {
        private IpPort ipPort;
    }
}
