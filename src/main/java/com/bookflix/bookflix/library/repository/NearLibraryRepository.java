package com.bookflix.bookflix.library.repository;

import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NearLibraryRepository extends JpaRepository<NearLibrary, Long> {
    List<NearLibrary> findAllByUser(User user);
}
