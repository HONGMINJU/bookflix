package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.user.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecommendServiceImpl implements RecommendService{

    private final RecommendRepository recommendRepository;
}
