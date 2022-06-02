package com.bookflix.bookflix.review.service;

import com.bookflix.bookflix.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
}
