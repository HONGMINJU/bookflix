package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.user.dto.request.PostUserReq;
import com.bookflix.bookflix.user.dto.request.PutUserReq;
import com.bookflix.bookflix.user.dto.response.GetUser;
import com.bookflix.bookflix.user.dto.response.PostUserRes;

public interface UserService {
    public PostUserRes createUser(Long userId, PostUserReq postUserReq);
    public void updateUser(Long userId, PutUserReq putUserReq);
    public GetUser findById(Long id);
}
