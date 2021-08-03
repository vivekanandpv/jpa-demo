package com.example.jpademo.controllers;

import com.example.jpademo.models.Book;
import com.example.jpademo.repositories.BookRepository;
import com.example.jpademo.services.IBookService;
import com.example.jpademo.viewmodels.BookViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookViewModel>> get() {
        return ResponseEntity.ok(bookService.get());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookViewModel> get(@PathVariable int id) {
        return ResponseEntity.ok(bookService.get(id));
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookViewModel viewModel) {
        this.bookService.create(viewModel);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookViewModel viewModel) {
        this.bookService.update(viewModel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleExceptions(RuntimeException exception) {
        return ResponseEntity.ok(exception.getMessage());
    }
}
