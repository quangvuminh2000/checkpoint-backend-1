package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.repository.PersonRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getByNameAndAge(String name, Integer age) {
        Optional<Person> personByNameAndAge = personRepository.findPersonByNameAndAge(name, age);

        if (personByNameAndAge.isPresent()) {
            return personByNameAndAge.get();
        }
        else {
            throw new RuntimeException("Cannot find exact match person in DB");
        }
    }

    public void create(Person person) {
        Optional<Person> personByNameAndAge = personRepository.findPersonByNameAndAge(person.getName(), person.getAge());

        if (personByNameAndAge.isPresent()) {
            throw new IllegalStateException("Person has been added into the Database");
        }

        // Save when non-exist person in the database
        personRepository.save(person);

    }

    public void deleteFirstFoundByNameAndAge(String name, Integer age) {
        Optional<Person> personByNameAndAge = personRepository.findPersonByNameAndAge(name, age);

        if (personByNameAndAge.isPresent()) {
            try {
                personRepository.deleteByNameAndAge(name, age);
            } catch (InvalidDataAccessApiUsageException queryError) {
                System.out.println("Error appear in delete repository");
                System.err.println(queryError.getMessage());
            }
        }
        else {
            throw new RuntimeException(String.format("Cannot find exact match person with %s-%d in DB", name, age));
        }
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }
}
