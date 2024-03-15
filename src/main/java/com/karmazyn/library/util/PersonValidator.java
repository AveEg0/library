package com.karmazyn.library.util;

import com.karmazyn.library.dao.PersonDao;
import com.karmazyn.library.models.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

public class PersonValidator implements Validator {
    private final PersonDao personDao;

    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person targetPerson = (Person) target;
        Optional<Person> person = personDao.show(targetPerson.getName());

        if (person.isPresent() && !Objects.equals(person.get().getId(), targetPerson.getId())) {
            errors.rejectValue("name", "", "Name is taken");
        }
    }
}
