package com.example.Library_System_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowDTO extends HasIntIdDTO{

        private Long borrowDate;

        private Integer duration;

        private Long userId;

        private Long bookId;

}
