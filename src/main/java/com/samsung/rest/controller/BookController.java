package com.samsung.rest.controller;

import com.samsung.domain.Book;
import com.samsung.rest.dto.BookDto;
import com.samsung.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {

        List<BookDto> bookDtoList = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        return bookDtoList;

    }

    @PostMapping("/book")
    public BookDto insertBook(
            @RequestParam String nameBook,
            @RequestParam String nameAuthor,
            @RequestParam String nameGenre
    ){
        Book book = bookService.insert(nameBook, nameAuthor, nameGenre);
        return BookDto.toDto(book);
    }

    @PutMapping("/book/{id}")
    public BookDto updateBook(@PathVariable int id,
                              @RequestParam String nameBook,
                              @RequestParam String nameAuthor,
                              @RequestParam String nameGenre){
        Book book = bookService.update(id, nameBook, nameAuthor, nameGenre);
        return BookDto.toDto(book);
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable int id){

        return BookDto.toDto(bookService.getById(id));
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.deleteById(id);
    }
}
