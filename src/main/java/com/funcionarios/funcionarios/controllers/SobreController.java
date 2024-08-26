package com.funcionarios.funcionarios.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SobreController {

    @GetMapping("/Sobre")
    public String sobre() {
        return "Home/sobre"; // Nome do template Thymeleaf
    }
}