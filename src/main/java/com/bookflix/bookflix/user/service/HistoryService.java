package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.user.entity.User;

public interface HistoryService {

    public void createAndAddHistory(User user, String isbn);
}
