package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.name = :name AND c.dob = :dob")
    Optional<Customer> findByNameAndDob(String name, LocalDate dob);

    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findAllByName(String name);

    @Query("SELECT c FROM Customer c WHERE c.dob = :dob")
    List<Customer> findAllByDob(LocalDate dob);

    @Query("SELECT c FROM Customer c WHERE c.city = :city")
    List<Customer> findAllByCity(String city);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.name = :name AND c.dob = :dob")
    void deleteByNameAndDob(String name, LocalDate dob);

    @Query("SELECT c FROM Customer c WHERE c.city = :city AND c.dob = :dob")
    List<Customer> findAllByCityAndDob(String city, LocalDate dob);
}
