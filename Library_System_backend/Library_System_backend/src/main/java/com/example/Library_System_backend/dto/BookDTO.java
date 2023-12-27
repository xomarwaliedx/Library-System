package com.example.Library_System_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@RequiredArgsConstructor
public class BookDTO extends HasLongIdDTO {
  private String bookName;

  private String author;

  private String genre;

  private String language;

  private int publicationYear;

  private int count;

}
