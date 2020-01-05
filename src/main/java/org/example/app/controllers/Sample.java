package org.example.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Sample {
    @GetMapping("/sample")
    public String sample(){
        return "home";
    }
}
