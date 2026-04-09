package com.example.demo.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping("/public")
    public String publicPage() {
        return "example/public";
    }

    @RequestMapping("/private")
    public String privatePage() {
        return "example/private";
    }
}
