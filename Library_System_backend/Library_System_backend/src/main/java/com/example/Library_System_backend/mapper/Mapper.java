package com.example.Library_System_backend.mapper;

import com.example.Library_System_backend.dto.BookDTO;
import com.example.Library_System_backend.dto.BorrowDTO;
import com.example.Library_System_backend.dto.UserDTO;
import com.example.Library_System_backend.entity.Book;
import com.example.Library_System_backend.entity.Borrow;
import com.example.Library_System_backend.entity.User;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

import java.util.List;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface Mapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> userListToUserDTOList(List<User> users);

    User userDTOToUser(UserDTO userDTO);

    List<User> userDTOListToUserList(List<UserDTO> userDTOs);

    BookDTO bookToBookDTO(Book book);

    List<BookDTO> bookListToBookDTOList(List<Book> books);

    Book bookDTOToBook(BookDTO bookDTO);

    List<Book> bookDTOListToBookList(List<BookDTO> bookDTOs);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    Borrow borrowDTOToBorrow(BorrowDTO borrowDTO);

    List<Borrow> borrowDTOListToBorrowList(List<BorrowDTO> borrowDTOs);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    BorrowDTO borrowToBorrowDTO(Borrow borrow);

    List<BorrowDTO> borrowListToBorrowDTOList(List<Borrow> borrows);
}
