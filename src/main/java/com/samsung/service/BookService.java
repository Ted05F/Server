package com.samsung.service;

import com.samsung.domain.Book;

import java.util.List;

public interface BookService {

    Book insert(
        String nameBook,
        String nameAuthor,
        String nameGenre
    );

    Book update(
            int id,
            String nameBook,
            String nameAuthor,
            String nameGenre
    );


    List<Book> getAll();

    Book getById(int id);

    List<Book> getByName(String nameBook);

    void deleteById(int id);
}
