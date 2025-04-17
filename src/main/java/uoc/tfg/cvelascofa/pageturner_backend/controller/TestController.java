package uoc.tfg.cvelascofa.pageturner_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "¡Hola! Todo funciona correctamente 🚀";
    }
}
