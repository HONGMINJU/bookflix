package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.user.dto.response.GetBorrowHistoryRes;
import com.bookflix.bookflix.user.dto.response.GetReadHistoryRes;
import com.bookflix.bookflix.user.entity.User;

public interface HistoryService {

    public void createAndAddHistory(User user, String isbn);
    public GetBorrowHistoryRes getBorrowHistory(Long userId);
    public GetReadHistoryRes getReadHistory(Long userId);
    public void postReadHistory(Long userId, String isbn);
}
