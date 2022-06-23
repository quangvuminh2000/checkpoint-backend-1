package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Customer;
import com.mycompany.myapp.service.CustomerService;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*Post methods*/
    @PostMapping("/create")
    public void create(@RequestBody Customer customer) {
        customerService.create(customer);
    }

    /*Get methods*/
    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/same/name")
    public List<Customer> getSameNameField(
        @RequestParam(name="name", required = true) String name
    ) {
        return customerService.getSameName(name);
    }

    @GetMapping("/same/dob")
    public List<Customer> getSameDobField(
        @RequestParam(name="dob") String dob_string
        ) {
        return customerService.getSameDob(dob_string);
    }

    @GetMapping("/same/city")
    public List<Customer> getSameCityField(
        @RequestParam(name="city") String city
    ) {
        return customerService.getSameCity(city);
    }

    /*Delete Methods*/
    @DeleteMapping("/delete")
    public void deleteCustomer(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "dob") String dob_string
    ) {
        customerService.deleteFirstFoundByNameAndDob(name, dob_string);
    }
}
