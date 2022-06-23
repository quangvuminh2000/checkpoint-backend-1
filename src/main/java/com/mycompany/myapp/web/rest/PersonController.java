package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public Person getPerson(@RequestParam(name="name") String name, @RequestParam(name="age") Integer age) {
        try {
            return personService.getByNameAndAge(name, age);
        } catch(RuntimeException notFound) {
            System.err.println(notFound.getMessage());
            return new Person("Notfound", 0);
        }
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        return personService.getPeople();
    }

    @GetMapping("/hello/")
    public List<String> sayHelloPerson(@RequestParam(name="name") String name, @RequestParam(name="age") Integer age) {
        try {
            Person person = personService.getByNameAndAge(name, age);

            return List.of(
                "Hello, " + person.getName(),
                "Your Age is " + person.getAge()
            );
        } catch(RuntimeException notFound) {
            System.err.println(notFound.getMessage());
            return List.of(
                "Person with name " + name,
                "Age " + age,
                "Cannot be found in the database"
            );
        }
    }

    @PostMapping("/create")
    public void createNewPerson(@RequestBody Person person) {
        personService.create(person);
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestParam String name, @RequestParam Integer age) {
        personService.deleteFirstFoundByNameAndAge(name, age);
    }

}
