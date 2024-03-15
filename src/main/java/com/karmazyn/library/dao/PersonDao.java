package com.karmazyn.library.dao;

import com.karmazyn.library.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Person> personRowMapper;

    public PersonDao(JdbcTemplate jdbcTemplate, RowMapper<Person> personRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personRowMapper = personRowMapper;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT person_id, full_name, date_of_birth FROM person", personRowMapper);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (full_name, date_of_birth) VALUES (?,?)",
                person.getName(), Date.valueOf(person.getDateOfBirth()));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[] {id}, personRowMapper)
                .stream().findFirst().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name = ?", new Object[] {name}, personRowMapper)
                .stream().findFirst();
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?", id);
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE person SET full_name = ?, date_of_birth = ? WHERE person_id = ?",
                person.getName(), person.getDateOfBirth(), id);
    }
}
