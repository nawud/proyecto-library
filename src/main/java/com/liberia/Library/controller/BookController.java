package com.liberia.Library.controller;

import com.liberia.Library.model.Book;
import com.liberia.Library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
