package com.bookflix.bookflix.user.controller;

import com.bookflix.bookflix.user.dto.request.PutUserReq;
import com.bookflix.bookflix.user.service.HistoryService;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.user.dto.request.PostUserReq;
import com.bookflix.bookflix.user.dto.response.GetUser;
import com.bookflix.bookflix.user.dto.response.PostUserRes;
import com.bookflix.bookflix.user.service.RecommendService;
import com.bookflix.bookflix.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.INVALID_USERID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final HistoryService historyService;
    private final RecommendService recommendService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostUserRes> postUser(@Valid @RequestBody PostUserReq postUser) {
        Long userId = jwtService.getUserIdx();
        PostUserRes postUserRes = userService.createUser(userId, postUser);
        return new BaseResponse<>(postUserRes);
    }

    @GetMapping("/{id}")
    public BaseResponse<GetUser> getUser(@PathVariable("id") Long id) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        GetUser getUserRes = userService.findById(id);
        return new BaseResponse<>(getUserRes);
    }

    @PutMapping("/{id}")
    public BaseResponse<GetUser> updateUser(@PathVariable("id") Long id, @Valid @RequestBody PutUserReq putUserReq) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        userService.updateUser(id, putUserReq);
        return new BaseResponse<>(null);
    }

}
