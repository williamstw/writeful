package org.notapache.writeful.domain;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
    Person findOne(Long id);

}
