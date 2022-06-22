package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public Person getPerson(String name, Integer age) {
        return new Person(name, age);
    }
}
