package com.samsung.repository;

import com.samsung.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void shouldInsertAuthor() {

        Author expectedAuthor = Author.builder()
                .id(4)
                .name("Ivan")
                .build();

        authorRepository.save(expectedAuthor);
        Author actualAuthor = authorRepository.getById(4);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("должен удалять автора по id")
    void shouldDeleteAuthorById() {

        int size = authorRepository.findAll().size();

        authorRepository.deleteById(2);
        entityManager.flush();


        int size1 =authorRepository.findAll().size();
        assertThat(size).isEqualTo(size1 + 1);

    }

}