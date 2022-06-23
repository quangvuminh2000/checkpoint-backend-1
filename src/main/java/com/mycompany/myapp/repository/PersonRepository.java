package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // SELECT p FROM jhi_person p WHERE p.name=? AND p.age=?
    @Query("SELECT p FROM Person p WHERE p.name = ?1 AND p.age = ?2")
    Optional<Person> findPersonByNameAndAge(String name, Integer age);

    @Modifying
    @Transactional
    @Query("DELETE FROM Person p WHERE p.name = :name AND p.age = :age")
    void deleteByNameAndAge(@Param("name") String name, @Param("age") Integer age);
}
