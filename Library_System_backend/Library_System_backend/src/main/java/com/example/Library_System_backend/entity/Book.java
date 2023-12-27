package com.example.Library_System_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class Book extends HasLongId {

    @Column(name = "book_name", unique = true, nullable = false)
    private String bookName;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "publicationYear", nullable = false)
    private Integer publicationYear;

    @Column(name = "count", nullable = false)
    private Integer count;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Borrow> borrow = new HashSet<>();

}
