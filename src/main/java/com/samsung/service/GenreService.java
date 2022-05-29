package com.samsung.service;


import com.samsung.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre insert(Genre genre);

    List<Genre> getAll();

    Genre getById(int id);

    Genre getByName(String name);

    Genre update(int id, String nameGenre);

    void deleteById(int id);

}

