package com.example.Library_System_backend.repository;

import com.example.Library_System_backend.entity.Book;
import com.example.Library_System_backend.entity.Borrow;
import com.example.Library_System_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByUser_Id(Long userId);

    List<Borrow> findByUser_IdAndBook_Id(Long userId, Long BookId);

    List<Borrow> findAll();
    
    Optional<Borrow> findById(Integer id);

    @Transactional
    @Modifying
    @Query("delete from Borrow b where b.id in :ids")
    Integer deleteByIdIn(@Param("ids") Integer id);

    //void deleteById(Integer id);
}
