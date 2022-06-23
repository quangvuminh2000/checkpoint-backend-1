package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/*
 * A class to store Customer information
 * */
@Entity
@Table(name = "jhi_customer")
public class Customer extends AbstractAuditingEntity implements Serializable {
    @Id
    @SequenceGenerator(sequenceName = "customer_sequence", name = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "dob")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;

    @Transient
    @Column(name = "age")
    private Integer age;

    public Customer(String name, LocalDate dob, String city) {
        this.name = name;
        this.dob = dob;
        this.city = city;
    }

    public Customer() { }

    /* Getter */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /* Setter */
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
