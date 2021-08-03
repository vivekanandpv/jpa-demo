package com.example.jpademo.viewmodels;

import com.example.jpademo.models.Book;

import java.util.ArrayList;
import java.util.List;

public class PublisherViewModel {
    private int id;
    private String name;
    private String email;
    private String country;
    private int sinceYear;
    private List<BookViewModel> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSinceYear() {
        return sinceYear;
    }

    public void setSinceYear(int sinceYear) {
        this.sinceYear = sinceYear;
    }

    public List<BookViewModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookViewModel> books) {
        this.books = books;
    }
}
