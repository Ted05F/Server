package com.samsung.service;

import com.samsung.domain.Genre;
import com.samsung.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    public Genre insert(Genre genre) {


        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(int id) {
        return genreRepository.getById(id);
    }

    @Override
    public Genre getByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public Genre update(int id, String nameGenre) {

        Genre genre = Genre.builder()
                .id(id)
                .name(nameGenre)
                .build();

        return genreRepository.save(genre);
    }

    @Override
    public void deleteById(int id) {

        genreRepository.deleteById(id);
    }
}
