package com.bookflix.bookflix.user.controller;

import com.bookflix.bookflix.common.response.BaseResponseResultStatus;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.library.service.LibraryService;
import com.bookflix.bookflix.user.dto.request.PutUserReq;
import com.bookflix.bookflix.user.dto.response.*;
import com.bookflix.bookflix.user.service.HistoryService;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.user.dto.request.PostUserReq;
import com.bookflix.bookflix.user.service.RecommendService;
import com.bookflix.bookflix.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.INVALID_USERID;
import static com.bookflix.bookflix.common.response.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final HistoryService historyService;
    private final RecommendService recommendService;

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

    @GetMapping("/{id}/books/borrow")
    public BaseResponse<GetBorrowHistoryRes> getUserBorrowHistory(@PathVariable("id") Long id) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        return new BaseResponse<>(historyService.getBorrowHistory(userId));
    }

    @GetMapping("/{id}/books/read")
    public BaseResponse<GetReadHistoryRes> getUserReadHistory(@PathVariable("id") Long id) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        return new BaseResponse<>(historyService.getReadHistory(userId));
    }

    @GetMapping("/{id}/recommend")
    public BaseResponse<GetRecommendList> getUserRecommendList(@PathVariable("id") Long id) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        return new BaseResponse<>(recommendService.getTotalRecommendList(userId));
    }

    @PostMapping("/{id}/books/read")
    public BaseResponse<BaseResponseStatus> postUserReadHistory(@PathVariable("id") Long id, @RequestBody Map<String, String> isbnInfo) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        historyService.postReadHistory(userId, isbnInfo.get("isbn"));
        return new BaseResponse<>(SUCCESS);
    }

    @PutMapping("/{id}")
    public BaseResponse<BaseResponseResultStatus> updateUser(@PathVariable("id") Long id, @Valid @RequestBody PutUserReq putUserReq) {
        Long userId = jwtService.getUserIdx();
        if (userId != id)
            throw new BaseException(INVALID_USERID);
        userService.updateUser(id, putUserReq);
        return new BaseResponse<>(BaseResponseResultStatus.SUCCESS);
    }

}
