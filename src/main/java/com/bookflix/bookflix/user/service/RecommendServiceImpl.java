package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.dto.response.BookInfo;
import com.bookflix.bookflix.book.dto.response.GetSimilarBookListRes;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.book.service.BookService;
import com.bookflix.bookflix.book.service.BookServiceUtil;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.user.dto.response.GetRecommendList;
import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.Recommend;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import com.bookflix.bookflix.user.repository.RecommendRepository;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{


    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final RecommendRepository recommendRepository;

    private final HistoryRepository historyRepository;

    private final BookServiceUtil bookServiceUtil;


    @Override
    public GetSimilarBookListRes getSimilarBookList(List<String> isbnList){
        List<String> similarISBNList = bookServiceUtil.getSimilarISBNListFromML(isbnList);
        List<Book> bookList = new ArrayList<>();
        for(String similarISBN : similarISBNList) {
            bookRepository.findById(similarISBN).ifPresentOrElse(
                    book -> bookList.add(book), () -> bookList.add(bookServiceUtil.postBook(similarISBN)));
        }
        List<BookInfo> similarBookList = bookList.stream().map(BookInfo::of).collect(Collectors.toList());
        return new GetSimilarBookListRes(similarBookList);
    }

    @Override
    public List<Book> getBookRecommendList(List<String> isbnList){
        List<Book> bookList = new ArrayList<>();
        List<String> recommendISBNList = bookServiceUtil.getISBNListFromML(isbnList);
        for(String recommendISBN : recommendISBNList){
            bookRepository.findById(recommendISBN).ifPresentOrElse(
                    book -> bookList.add(book), () -> bookList.add(bookServiceUtil.postBook(recommendISBN)));
        }
        return bookList;
    }

    @Override
    public List<Book> getNewBookRecommendList(List<String> isbnList){
        List<Book> bookList = new ArrayList<>();
        List<String> recommendISBNList = bookServiceUtil.getNewISBNListFromML(isbnList);
        for(String recommendISBN : recommendISBNList){
            bookRepository.findById(recommendISBN).ifPresentOrElse(
                    book -> bookList.add(book), () -> bookList.add(bookServiceUtil.postBook(recommendISBN)));
        }
        return bookList;
    }

    @Override
    public List<Book> getBestSellerList(User user){
        List<Book> bookList = new ArrayList<>();
        List<String> bestSellerList = bookServiceUtil.getBestSeller(user);
        for(String bestSellerISBN : bestSellerList){
            bookRepository.findById(bestSellerISBN).ifPresentOrElse(
                    book -> bookList.add(book), () -> bookList.add(bookServiceUtil.postBook(bestSellerISBN)));
        }
        return bookList;
    }

    @Override
    public GetRecommendList getTotalRecommendList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        List<String> isbnList = historyRepository.findHistoriesByUserAndBookStatus(user, BookStatus.READ)
                .stream().map(History::getBook).map(Book::getId).collect(Collectors.toList());

        List<BookInfo> userRecommendList = recommendRepository.findByUser(user).stream()
                                                    .map(Recommend::getBook).map(BookInfo::of).collect(Collectors.toList());
        List<BookInfo> newRecommendList = getNewBookRecommendList(isbnList).stream()
                                                    .map(BookInfo::of).collect(Collectors.toList());
        List<BookInfo> bestSeller = getBestSellerList(user).stream()
                                                    .map(BookInfo::of).collect(Collectors.toList());
        return new GetRecommendList(userRecommendList, newRecommendList, bestSeller);
    }

    @Override
    @Transactional
    public void clearRecommendList(User user){
        List<Recommend> recommendList = user.getRecommendList();
        for (Recommend recommend: recommendList)
            recommendRepository.delete(recommend);
        user.clearRecommend();
    }

    @Override
    @Transactional
    public void setRecommendList(User user, List<String> isbnList){
        List<Book> bookList = getBookRecommendList(isbnList);
        clearRecommendList(user);
        for (Book book : bookList){
            Recommend recommend = recommendRepository.save(new Recommend(user, book));
            user.addRecommend(recommend);
            System.out.println("recommend.getId() = " + recommend.getId());
        }
    }

    @Override
    public void test(List<String> isbnList){
        List<Book> bookList = getBookRecommendList(isbnList);
    }
}
