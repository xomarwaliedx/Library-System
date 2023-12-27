package com.example.Library_System_backend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowDTO extends HasIntIdDTO{

        private LocalDate borrowDate;

        private Integer duration;

        private Long userId;

        private Long bookId;

}
