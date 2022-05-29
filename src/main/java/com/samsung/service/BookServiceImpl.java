package com.samsung.service;

import com.samsung.domain.Author;
import com.samsung.domain.Book;
import com.samsung.domain.Genre;
import com.samsung.repository.AuthorRepository;
import com.samsung.repository.BookRepository;
import com.samsung.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Book insert(String nameBook, String nameAuthor, String nameGenre) {

        Author author = authorRepository.findByName(nameAuthor);

        if (author == null) {
            author = Author.builder()
                    .name(nameAuthor)
                    .build();
        }

        Genre genre = genreRepository.findByName(nameGenre);
        if (genre == null) {
            genre = Genre.builder()
                    .name(nameGenre)
                    .build();
        }

        Book book = Book.builder()
                .name(nameBook)
                .author(author)
                .genre(genre)
                .build();

        return bookRepository.save(book);
    }

    @Override
    public Book update(int id, String nameBook, String nameAuthor, String nameGenre) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(int id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getByName(String nameBook) {

        return bookRepository.findByName(nameBook);
    }

    @Override
    public void deleteById(int id) {

        bookRepository.deleteById(id);

    }
}
