package com.bookflix.bookflix.user.repository;

import com.bookflix.bookflix.user.entity.Recommend;
import com.bookflix.bookflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    List<Recommend> findByUser(User user);
}
