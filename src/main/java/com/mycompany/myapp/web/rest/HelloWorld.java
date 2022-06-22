package com.mycompany.myapp.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("helloworld")
public class HelloWorld {

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
