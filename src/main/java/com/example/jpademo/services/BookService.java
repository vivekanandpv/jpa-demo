package com.example.jpademo.services;

import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.models.Book;
import com.example.jpademo.models.Publisher;
import com.example.jpademo.repositories.BookRepository;
import com.example.jpademo.repositories.PublisherRepository;
import com.example.jpademo.viewmodels.BookViewModel;
import com.example.jpademo.viewmodels.PublisherViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<BookViewModel> get() {
        //  get List<Book>
        //  convert this to List<BookViewModel> and return
        return getModern();
    }

    @Override
    public BookViewModel get(int id) {
        Book bookDb = this.bookRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find book with id " + id));

        return toViewModel(bookDb);
    }

    @Override
    public void create(BookViewModel viewModel) {
        this.bookRepository.saveAndFlush(toModel(viewModel));
    }

    @Override
    public void update(BookViewModel viewModel) {
        Book bookDb = this.bookRepository.findById(viewModel.getId())
                .orElseThrow(() -> new RecordNotFoundException("Could not find book with id " + viewModel.getId()));

        bookDb.setTitle(viewModel.getTitle());
        bookDb.setEdition(viewModel.getEdition());
        bookDb.setnPages(viewModel.getnPages());
        bookDb.setPrice(viewModel.getPrice());

        bookRepository.saveAndFlush(bookDb);
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    private List<BookViewModel> getTraditional() {
        List<Book> bookEntities = bookRepository.findAll();

        List<BookViewModel> vmList = new ArrayList<>();

        for(Book b: bookEntities) {
            BookViewModel vm = toViewModel(b);
            vmList.add(vm);
        }

        return vmList;
    }

    private BookViewModel toViewModel(Book b) {
        BookViewModel vm = new BookViewModel();
        vm.setId(b.getId());
        vm.setEdition(b.getEdition());
        vm.setnPages(b.getnPages());
        vm.setTitle(b.getTitle());
        vm.setPrice(b.getPrice());


        PublisherViewModel publisher = new PublisherViewModel();
        publisher.setCountry(b.getPublisher().getCountry());
        publisher.setEmail(b.getPublisher().getEmail());
        publisher.setName(b.getPublisher().getName());
        publisher.setId(b.getPublisher().getId());
        publisher.setSinceYear(b.getPublisher().getSinceYear());

        vm.setPublisher(publisher);

        return vm;
    }

    private Book toModel(BookViewModel viewModel) {
        Book book = new Book();
        book.setId(viewModel.getId());
        book.setEdition(viewModel.getEdition());
        book.setnPages(viewModel.getnPages());
        book.setTitle(viewModel.getTitle());
        book.setPrice(viewModel.getPrice());

        Publisher publisherDb = this.publisherRepository.findById(viewModel.getPublisherId())
                .orElseThrow(() -> new RecordNotFoundException("Could not find the publisher with id " + viewModel.getPublisherId()));

        book.setPublisher(publisherDb);
        publisherDb.getBooks().add(book);

        return book;
    }

    private List<BookViewModel> getModern() {
        return bookRepository.findAll()
                .stream()
                .map(b -> {
                    return toViewModel(b);
                })
                .collect(Collectors.toList());
    }
}
