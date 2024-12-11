package com.liberia.Library.controller;

import com.liberia.Library.model.Book;
import com.liberia.Library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/book")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @PostMapping("/book")
    public void createBook (@RequestBody Book newBook) {
         bookService.addBook(newBook);
    }


    @DeleteMapping("/book/{id}")
    public void deleteBookByid(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}
