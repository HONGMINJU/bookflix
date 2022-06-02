package com.bookflix.bookflix.oauth.service;

import com.bookflix.bookflix.oauth.dto.response.LoginRes;

public interface OauthService {

    LoginRes socialLogin(String socialType, String accessToken);
}
