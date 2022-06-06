package com.bookflix.bookflix.book.repository;

import com.bookflix.bookflix.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContaining(String keyword);
}
