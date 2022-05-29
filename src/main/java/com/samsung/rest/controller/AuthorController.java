package com.samsung.rest.controller;


import com.samsung.domain.Author;
import com.samsung.rest.dto.AuthorDto;
import com.samsung.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author")
    public List<AuthorDto> getAllAuthor(){

        return authorService
                .getAll()
                .stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/author")
    public AuthorDto insertAuthor(@RequestBody AuthorDto authorDto){

        Author author = authorService.insert(AuthorDto.toDomainObject(authorDto));
        return AuthorDto.toDto(author);
    }

    @PutMapping("/author/{id}")
    public AuthorDto updateAuthor(@PathVariable int id,
                                  @RequestParam String name){
        Author author = authorService.update(id, name);
        return AuthorDto.toDto(author);

    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable int id){

        authorService.deleteById(id);
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable int id){

        Author author = authorService.getById(id);
        return AuthorDto.toDto(author);
    }

}
