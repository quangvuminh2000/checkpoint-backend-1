package com.mycompany.myapp.config;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person henry = new Person("Henry", 22);
            Person tony = new Person("Tony", 14);
            Person minh = new Person("Minh", 13);

            repository.saveAll(List.of(henry, tony, minh));
        };
    }

}
