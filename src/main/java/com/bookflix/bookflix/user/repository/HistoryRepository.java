package com.bookflix.bookflix.user.repository;

import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserAndbookStatus(User user, BookStatus bookStatus);
}
