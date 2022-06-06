package com.bookflix.bookflix.review.repository;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.review.entity.Review;
import com.bookflix.bookflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByWriterAndBook(User user, Book book);
}
