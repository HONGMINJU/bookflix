package com.bookflix.bookflix.library.repository;

import com.bookflix.bookflix.library.entity.NearLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NearLibraryRepository extends JpaRepository<NearLibrary, Long> {
}
