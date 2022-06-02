package com.bookflix.bookflix.oauth.controller;

import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.oauth.dto.response.LoginRes;
import com.bookflix.bookflix.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/login/{socialType}")
    public BaseResponse<LoginRes> login(@PathVariable("socialType") String socialType, @RequestBody String accessToken) {
        LoginRes loginRes = oauthService.socialLogin(socialType, accessToken);
        return new BaseResponse<>(loginRes);
    }

}
