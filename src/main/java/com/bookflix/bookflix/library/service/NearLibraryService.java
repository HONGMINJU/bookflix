package com.bookflix.bookflix.library.service;

import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.user.entity.User;

public interface NearLibraryService {

    public void createAndAddNearLibrary(User user, Long libraryId);
}
