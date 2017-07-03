package org.notapache.writeful.web;

import org.notapache.writeful.domain.Person;
import org.notapache.writeful.domain.PersonRepository;
import org.notapache.writeful.forms.*;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


@RestController
@ExposesResourceFor(Person.class)
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository repository;
    private EntityLinks entityLinks;
    private FormAdapter adapter;


    public PersonController(PersonRepository repository, EntityLinks entityLinks, FormAdapter adapter) {
        this.repository = repository;
        this.entityLinks = entityLinks;
        this.adapter = adapter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resources<Person>> showPeople() {
        Resources<Person> resources = new Resources<Person>(this.repository.findAll());
        resources.add(this.entityLinks.linkToCollectionResource(Person.class));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(path = "/template", produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Forms> showTemplate() throws Exception {

        Forms forms = adapter.createDefault(new Person());

        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resource<Person>> showPerson(@PathVariable Long id) {
        Resource<Person> resource = new Resource<>(this.repository.findOne(id));
        resource.add(linkTo(methodOn(PersonController.class).showPerson(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



}