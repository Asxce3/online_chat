package org.example.user_service.service;

import org.example.user_service.DAO.PersonDAO;
import org.example.user_service.exception.PersonNotFoundException;
import org.example.user_service.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Service {
    @Autowired
    PersonDAO personDAO;

    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    public Person getPersonById(int id) {
        Optional<Person> optionalPerson = personDAO.getPersonById(id);

        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        }
        throw new PersonNotFoundException("Person not found");
    }


    public void createPerson(Person person) {
        personDAO.createPerson(person);
    }

    public void deletePersonById(int id) {
        personDAO.deletePersonById(id);
    }
}
