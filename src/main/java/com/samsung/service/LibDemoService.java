package com.samsung.service;

import com.samsung.domain.Author;
import com.samsung.domain.Book;
import com.samsung.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibDemoService {

    private final AuthorService authorService;

    private final BookService bookService;

    private final GenreService genreService;

    public void authorDemo() {

        List<Author> authorList = authorService.getAll();

        System.out.println("=========");
        for (Author author : authorList) {

            System.out.println(author);
        }
        System.out.println("=========");


        authorService.update(1, "Иван");
        authorList = authorService.getAll();

        System.out.println("=========");
        for (Author author1 : authorList) {

            System.out.println(author1);
        }
        System.out.println("=========");


    }

    @Transactional
    public void bookDemo() {

        List<Book> bookList = bookService.getAll();
        for (Book book1 : bookList) {

            System.out.println(book1.getName() + ":");
            System.out.println(book1.getAuthor().getName() + "," + book1.getGenre().getName());

            List<Comment> commentList = book1.getCommentList();

            for (Comment comment : commentList) {

                System.out.println(comment.getContent());
            }

        }

        bookService.insert("Длинная нога",
                "Антон",
                "Драма"
                );

            bookList = bookService.getAll();

        for (Book book1 : bookList) {

            System.out.println(book1.getName() + ":");
            System.out.println(book1.getAuthor().getName() + "," + book1.getGenre().getName());

            List<Comment> commentList = book1.getCommentList();


            if (commentList != null) {
                for (Comment comment : commentList) {

                    System.out.println(comment.getContent());
                }
            }
        }

    }

//    @Transactional
//    public void commentDemo() {
//
//        commentRepository.updateCommentById(1, "very good");
//
//        List<Comment> commentList = commentRepository.findAll();
//
//        for (Comment comment : commentList) {
//
//            System.out.println(comment.getId() + " - " + comment.getContent());
//
//        }
//
//        commentList = commentRepository.findByBookId(2);
//
//        for (Comment comment : commentList) {
//
//            System.out.println(comment.getId() + " - " + comment.getContent());
//
//        }
//
//    }

}
