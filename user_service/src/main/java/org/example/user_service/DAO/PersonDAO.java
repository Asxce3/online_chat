package org.example.user_service.DAO;

import org.example.user_service.DAO.utils.DaoUtils;
import org.example.user_service.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    DaoUtils daoUtils = new DaoUtils();

    public List<Person> getAllPersons() {
        try {
            return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));

        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Person> getPersonById(int id) {
        try {
            return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createPerson(Person person) {
        try {
            jdbcTemplate.update("INSERT INTO person (username, password) VALUES(?, ?)",
                    person.getUsername(),
                    person.getPassword());

        }   catch (DuplicateKeyException e) {
            Optional<String> key = daoUtils.getKeyName(e.getMessage());
            if (key.isPresent()) {
                throw new DuplicateKeyException("Current " + key.get() + " already exists");
            }

        }   catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());

        }  catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletePersonById(int id) {
        try {
            jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);

        }   catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
