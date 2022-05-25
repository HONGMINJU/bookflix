package com.bookflix.bookflix.common.response;

import lombok.Getter;

import static com.bookflix.bookflix.common.response.BaseResponseResultStatus.FAILED;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(BaseResponseResultStatus.SUCCESS, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : 회원 관련 실패
     */
    ;

    private final BaseResponseResultStatus resultStatus;
    private final int code;
    private final String message;

    private BaseResponseStatus(BaseResponseResultStatus resultStatus, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.resultStatus = resultStatus;
        this.code = code;
        this.message = message;
    }
}
