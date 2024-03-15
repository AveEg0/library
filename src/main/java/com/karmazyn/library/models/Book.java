package com.karmazyn.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.util.Objects;

public class Book {
    private int id;

    @NotEmpty(message = "Title is required")
    private String title;

    private String author;

    @Positive(message = "Year of publication should be positive")
    private int yearOfPublication;

    @Min(value = 0, message = "Person id should be positive")
    private int personId;

    public Book() { }

    public Book(int id, String title, String author, int yearOfPublication, int personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.personId = personId;
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && yearOfPublication == book.yearOfPublication && personId == book.personId && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, yearOfPublication, personId);
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean isFree() {
        return personId == 0;
    }
}
