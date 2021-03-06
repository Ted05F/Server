package com.samsung.rest.controller;

import com.samsung.domain.Comment;
import com.samsung.rest.dto.CommentDto;
import com.samsung.service.BookService;
import com.samsung.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final BookService bookService;

    @PostMapping("/comment")
    public CommentDto createNewComment(
            @RequestParam String content,
            @RequestParam int bookId
    ) {

        Comment comment = Comment.builder()
                .content(content)
                .book(bookService.getById(bookId))
                .build();


        return CommentDto.toDto(commentService.insert(comment));
    }

    @GetMapping("/comment")
    public List<CommentDto> getAllComments() {

        return commentService
                .getAll()
                .stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/comment/{id}/content")
    public void updateCommentById(
            @PathVariable int id,
            @RequestParam String content
    ) {

        commentService.updateByBookId(id, content);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteCommentById(@PathVariable int id) {

        commentService.deleteById(id);
    }

    @GetMapping("/book/{id}/comment")
    public List<CommentDto> getCommentsByBookId(@PathVariable int id) {

        return commentService
                .getByBookId(id)
                .stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }
}