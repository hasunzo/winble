package com.winble.server.adapter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String redirectSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
