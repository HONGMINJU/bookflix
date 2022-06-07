package com.bookflix.bookflix.common.advisor;

import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionAdvisor {

    @ExceptionHandler(value = {ExpiredJwtException.class})
    protected BaseResponse<String> handleExpiredJwtException(Exception e) {
        return new BaseResponse<>(BaseResponseStatus.EXPIRED_JWT);
    }

    @ExceptionHandler(value = {JwtException.class})
    protected BaseResponse<String> handleJwtException(Exception e) {
        return new BaseResponse<>(BaseResponseStatus.INVALID_JWT);
    }

    @ExceptionHandler(value = {BaseException.class})
    protected BaseResponse<String> handleBaseException(BaseException baseException) {
        return new BaseResponse<>(baseException.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    protected BaseResponse<String> handleException(Exception e) {
        System.out.println("e.getMessage() = " + e.getMessage());
        e.printStackTrace();
        return new BaseResponse<>(SERVER_ERROR);
    }
}