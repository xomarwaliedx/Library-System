package com.example.Library_System_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "borrow")
public class Borrow extends HasIntId {

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
