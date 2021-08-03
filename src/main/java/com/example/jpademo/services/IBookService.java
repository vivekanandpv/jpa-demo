package com.example.jpademo.services;

import com.example.jpademo.viewmodels.BookViewModel;

import java.util.List;

public interface IBookService {
    List<BookViewModel> get();
    BookViewModel get(int id);
    void create(BookViewModel viewModel);
    void update(BookViewModel viewModel);
    void delete(int id);
}
