package org.notapache.writeful.domain;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class FakePersonRepository implements  PersonRepository {
    Random random = new Random();
    @Override
    public List<Person> findAll() {
        List<Person> peeps = Lists.newArrayList();
        peeps.add(new Person("Mickey Mouse"));
        peeps.add(new Person("Minnie", null, "Mouse"));
        peeps.add(new Person("Donald", "the", "Duck"));
        return peeps;
    }

    @Override
    public Person findOne(Long id) {
        return findAll().get(random.nextInt(3));
    }
}
