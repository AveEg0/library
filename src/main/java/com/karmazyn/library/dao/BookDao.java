package com.karmazyn.library.dao;

import com.karmazyn.library.dao.mappers.PersonRowMapper;
import com.karmazyn.library.models.Book;
import com.karmazyn.library.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Book> bookRowMapper;
    private final PersonRowMapper personRowMapper;

    public BookDao(JdbcTemplate jdbcTemplate, RowMapper<Book> bookRowMapper, PersonRowMapper personRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
        this.personRowMapper = personRowMapper;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book;", bookRowMapper);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, year_of_publication) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[] {id}, bookRowMapper)
                .stream().findFirst().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year_of_publication = ? WHERE book_id = ? ",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication(), id);
    }

    public void assign(int id, int personId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?", personId, id);
    }

    public void free(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE book_id = ?", id);
    }

    public Optional<Person> getOwner(int id) {
        return jdbcTemplate.query("SELECT person.* FROM book " +
                "JOIN person USING(person_id) WHERE book_id = ?", personRowMapper, id).stream().findFirst();
    }
}
