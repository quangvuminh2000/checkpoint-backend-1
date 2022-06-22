package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{name}-{age}")
    public Person getPerson(@PathVariable(name="name") String name, @PathVariable(name="age") Integer age) {
        return personService.getPerson(name, age);
    }

    @GetMapping("/{name}-{age}/hello")
    public List<String> sayHelloPerson(@PathVariable(name="name") String name, @PathVariable(name="age") Integer age) {
        Person person = personService.getPerson(name, age);

        return List.of(
            "Hello, " + person.getName(),
            "Your Age is " + person.getAge()
        );
    }
}
