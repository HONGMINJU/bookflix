package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.User;

import java.util.List;

public interface RecommendService {
    public void clearRecommendList(User user);
    public void setRecommendList(User user, List<String> isbnList);
    public void test(List<String> isbnList);

}
