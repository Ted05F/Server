package com.samsung.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne(targetEntity = Author.class, fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch=FetchType.LAZY)
    @JoinColumn(name="genre_id")
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Comment> commentList;


}
