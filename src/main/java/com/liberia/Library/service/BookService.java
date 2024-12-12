package com.liberia.Library.service;

import com.liberia.Library.model.Book;
import com.liberia.Library.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
   private final IBookRepository IBookRepository;

    public BookService(com.liberia.Library.repository.IBookRepository iBookRepository) {
        IBookRepository = iBookRepository;
    }
    public List<Book> getAll() {
        return IBookRepository.findAll();
    }
    public void addBook(Book newBook) {
        IBookRepository.save(newBook);
    }

    public void deleteBook(Long id) {
        IBookRepository.deleteById(id);

    }
    public Optional<Book> findBook(Long id){
        return IBookRepository.findById(id);
    }
    public Book updatedBook(Long id, Book updatedBook){

        Optional<Book> foundBook = IBookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();


            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setIsbn(updatedBook.getIsbn());


            return IBookRepository.save(existingBook);
        }

        throw new RuntimeException("Book not found with id: " + id);
    }
}