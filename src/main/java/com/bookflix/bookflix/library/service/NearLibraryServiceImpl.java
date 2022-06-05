package com.bookflix.bookflix.library.service;

import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.library.repository.LibraryRepository;
import com.bookflix.bookflix.library.repository.NearLibraryRepository;
import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NearLibraryServiceImpl implements NearLibraryService{

    private final NearLibraryRepository nearLibraryRepository;

    private final LibraryRepository libraryRepository;

    @Override
    public void createAndAddNearLibrary(double distance, User user, Long libraryId){
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        NearLibrary nearLibrary = nearLibraryRepository.save(new NearLibrary(distance, user, library));
        user.addNearLibrary(nearLibrary);
    }
}
