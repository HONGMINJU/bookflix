package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.service.BookService;
import com.bookflix.bookflix.user.entity.Recommend;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{

    private final RecommendRepository recommendRepository;

    private final BookService bookService;

    @Override
    public void clearRecommendList(User user){
        List<Recommend> recommendList = user.getRecommendList();
        for (Recommend recommend: recommendList)
            recommendRepository.delete(recommend);
        user.clearRecommend();
    }

    @Override
    public void setRecommendList(User user, List<String> isbnList){
        List<Book> bookList = bookService.getBookRecommendList(isbnList);
        clearRecommendList(user);
        for (Book book : bookList){
            Recommend recommend = recommendRepository.save(new Recommend(user, book));
            user.addRecommend(recommend);
        }
    }

    @Override
    public void test(List<String> isbnList){
        List<Book> bookList = bookService.getBookRecommendList(isbnList);
    }
}
