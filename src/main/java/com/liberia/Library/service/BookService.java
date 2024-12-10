package com.liberia.Library.service;

import com.liberia.Library.model.Book;
import com.liberia.Library.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
   private final IBookRepository IBookRepository;

    public BookService(com.liberia.Library.repository.IBookRepository iBookRepository) {
        IBookRepository = iBookRepository;
    }
    public List<Book> getAll() {
        return IBookRepository.findAll();
    }

}
