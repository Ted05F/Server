package com.samsung.repository;

import com.samsung.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Класс BookDaoJPA")
@DataJpaTest
class BookRepositoryTest {

    private static final int EXISTING_ID1 = 1;
    private static final int EXISTING_AUTHOR_COUNT = 4;
    public static final int EXISTING_ID2 = 2;
    public static final int EXISTING_ID3 = 3;
    public static final int EXISTING_ID4 = 4;
    public static final String EXISTING_NAME1 = "Ночной дозор";
    public static final String EXISTING_NAME2 = "Лабиринты отражения";
    public static final String EXISTING_NAME3 = "Горе от ума";
    public static final String EXISTING_NAME4 = "Неукротимая планета";
    public static final int AUTHOR_ID1 = 1;
    public static final int GENRE_ID1 = 1;
    public static final int AUTHOR_ID2 = 2;
    public static final int GENRE_ID2 = 2;
    public static final int AUTHOR_ID3 = 3;
    public static final int GENRE_ID3 = 3;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("должен добавлять книгу")
    @Test
    void shouldInsertBook() {

        Book expectedBook = Book.builder()
                .id(5)
                .name("qwer")
                .author(authorRepository.getById(AUTHOR_ID1))
                .genre(genreRepository.getById(GENRE_ID1))
                .build();

        bookRepository.save(expectedBook);
        Book actualBook = bookRepository.getById(5);

        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DisplayName("должен обновлять книгу")
    @Test
    void shouldUpdateBook() {

        Book expectedBook1 = Book.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME2)
                .build();

        bookRepository.save(expectedBook1);
        Book actualBook = bookRepository.getById(EXISTING_ID1);

        assertThat(actualBook.getName()).isEqualTo(expectedBook1.getName());
    }

    @DisplayName("должен возвращать все книги")
    @Test
    void shouldGetAllBooks() {

        assertThat(bookRepository.findAll().size()).isEqualTo(EXISTING_AUTHOR_COUNT);

    }

    @DisplayName("должен возвращать книгу по id")
    @Test
    void shouldGetBookById() {

        Book expectedBook1 = Book.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .author(authorRepository.getById(AUTHOR_ID1))
                .genre(genreRepository.getById(AUTHOR_ID1))
                .build();

        Book actualBook = bookRepository.getById(EXISTING_ID1);

        assertThat(actualBook.getName()).isEqualTo(expectedBook1.getName());
        assertThat(actualBook.getId()).isEqualTo(expectedBook1.getId());
        assertThat(actualBook.getAuthor()).isEqualTo(expectedBook1.getAuthor());
        assertThat(actualBook.getGenre()).isEqualTo(expectedBook1.getGenre());
        assertThat(actualBook.getCommentList().size()).isEqualTo(2);
    }

    @DisplayName("должен возвращать книгу по имени")
    @Test
    void shouldGetAuthorByName() {

        Book expectedBook1 = Book.builder()
                .id(EXISTING_ID1)
                .name(EXISTING_NAME1)
                .author(authorRepository.getById(AUTHOR_ID1))
                .genre(genreRepository.getById(AUTHOR_ID1))
                .build();

        Book actualBook = (Book) bookRepository.findByName(EXISTING_NAME1);

        assertThat(actualBook.getName()).isEqualTo(expectedBook1.getName());
        assertThat(actualBook.getId()).isEqualTo(expectedBook1.getId());
        assertThat(actualBook.getAuthor()).isEqualTo(expectedBook1.getAuthor());
        assertThat(actualBook.getGenre()).isEqualTo(expectedBook1.getGenre());
        assertThat(actualBook.getCommentList().size()).isEqualTo(2);
    }

    @DisplayName("должен удалять книгу по id")
    @Test
    void shouldDeleteBookById() {


        bookRepository.deleteById(EXISTING_ID1);

        assertThatThrownBy(() -> bookRepository.getById(EXISTING_ID1)).isInstanceOf(JpaObjectRetrievalFailureException.class);
    }

}