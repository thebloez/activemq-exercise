package com.thebloez.demo.repository;

import com.thebloez.demo.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryPerson extends CrudRepository<Person, Long> {
}
