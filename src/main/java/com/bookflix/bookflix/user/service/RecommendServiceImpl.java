package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.user.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecommendServiceImpl implements RecommendService{

    private final RecommendRepository recommendRepository;
}
