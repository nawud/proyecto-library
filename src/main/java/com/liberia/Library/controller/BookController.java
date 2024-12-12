package com.liberia.Library.controller;

import com.liberia.Library.model.Book;
import com.liberia.Library.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable long id){
        Optional<Book> foundBook = bookService.findBook(id);
        if(foundBook.isPresent()){
            return new ResponseEntity<>(foundBook.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook){
        try {

            Book book = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            //en el caso de que no encuentre devuelve not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}