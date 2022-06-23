package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Customer;
import com.mycompany.myapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void create(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findByNameAndDob(customer.getName(), customer.getDob());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Customer has been added into the Database");
        }

        customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public List<Customer> getSameName(String name) {
        return customerRepository.findAllByName(name);
    }

    public List<Customer> getSameDob(String dob_string) {
        LocalDate dob = this.StringToLocalDate(dob_string);
        return customerRepository.findAllByDob(dob);
    }

    public List<Customer> getSameCity(String city) {
        return customerRepository.findAllByCity(city);
    }
    private LocalDate StringToLocalDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(string, formatter);

        return localDate;
    }
}
