package com.bookflix.bookflix.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.SUCCESS;


@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "result"})
public class BaseResponse<T> {//BaseResponse 객체를 사용할때 성공, 실패 경우

    @JsonProperty("status")
    private final BaseResponseResultStatus resultStatus;
    private final String message;
    private final int code;

    private T result;

    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.resultStatus = SUCCESS.getResultStatus();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this.resultStatus = status.getResultStatus();
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    public BaseResponse(BaseResponseStatus status, String msg) {
        this.resultStatus = status.getResultStatus();
        this.message = msg;
        this.code = status.getCode();
    }
}