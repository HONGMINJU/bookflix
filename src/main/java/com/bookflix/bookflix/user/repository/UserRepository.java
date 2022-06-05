package com.bookflix.bookflix.user.repository;

import com.bookflix.bookflix.user.entity.enumType.SocialType;
import com.bookflix.bookflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //boolean existsBySocialTypeAndOauthId(SocialType socialType, String oauthId);
    boolean existsByOauthId(String oauthId);
    boolean existsById(Long userId);
    Optional<User> findUserBySocialTypeAndOauthId(SocialType socialType, String oauthId);
}
