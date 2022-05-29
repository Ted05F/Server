package com.samsung.rest.dto;

import com.samsung.domain.Author;
import com.samsung.domain.Book;
import com.samsung.domain.Comment;
import com.samsung.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDto {

    private int id;

    private String name;

    private AuthorDto authorDto;

    private GenreDto genreDto;

    private List<CommentDto> commentDtoList;



    public static BookDto toDto(Book book){

        List<CommentDto> commentDtoList1 = new ArrayList<>();
        if (book.getCommentList() != null){
            commentDtoList1 = book.getCommentList()
                    .stream()
                    .map(CommentDto::toDto)
                    .collect(Collectors.toList());

        }else {

            commentDtoList1= new ArrayList<>();
        }


        return new BookDto(
                book.getId(),
                book.getName(),
                AuthorDto.toDto(book.getAuthor()),
                GenreDto.toDto(book.getGenre()),
                commentDtoList1

        );
    }

    public static GenreDto toDto(Genre genre) {

        return new GenreDto(
                genre.getId(),
                genre.getName()
        );
    }

}
