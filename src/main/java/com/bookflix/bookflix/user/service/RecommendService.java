package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.dto.response.GetSimilarBookListRes;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.user.dto.response.GetRecommendList;
import com.bookflix.bookflix.user.entity.User;

import java.util.List;

public interface RecommendService {

    public GetSimilarBookListRes getSimilarBookList(List<String> isbnList);
    public List<Book> getBookRecommendList(List<String> isbnList);
    public List<Book> getNewBookRecommendList(List<String> isbnList);
    public GetRecommendList getTotalRecommendList(Long userId);
    public List<Book> getBestSellerList(User user);

    public void clearRecommendList(User user);
    public void setRecommendList(User user, List<String> isbnList);
    public void test(List<String> isbnList);
}
