package com.mycompany.myapp.web.rest;


import com.mycompany.myapp.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloworld")
public class HelloWorld {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/{name}-{age}")
    public Person helloPerson(@PathVariable(name="name") String name, @PathVariable(name="age") Integer age) {
        return new Person(name, age);
    }

}
