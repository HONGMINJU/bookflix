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
     * 2000 : OAUTH 관련 실패
     */
    INVALID_JWT(FAILED, 2000, "유효하지 않은 JWT입니다."),
    EMPTY_JWT(FAILED, 2001, "빈 JWT입니다."),
    EXPIRED_JWT(FAILED, 2002, "만료된 JWT입니다."),
    POST_USERS_KAKAO_ERROR(FAILED, 2003, "카카오에서 정보를 가져오는 과정에서 에러가 발생했습니다."),
    POST_USERS_NAVER_ERROR(FAILED, 2004, "네이버에서 정보를 가져오는 과정에서 에러가 발생했습니다."),
    BEARER_NOT_FOUND(FAILED, 2005, "토큰 앞에 Bearer이 없습니다."),

    /**
     * 3000 : 사용자 관련 실패
     */
    USER_NOT_EXIST(FAILED, 3000, "존재하지 않는 user입니다."),
    INVALID_USERID(FAILED, 3001, "유효하지 않은 userId입니다."),

    /**
     * 4000 : 책 관련 실패
     */
    BOOK_NOT_EXIST(FAILED, 4000, "존재하지 않는 book입니다."),

    /**
     * 5000 : 책 관련 실패
     */
    REVIEW_NOT_EXIST(FAILED, 5000, "존재하지 않는 review입니다."),
    REVIEW_NOT_WRITER(FAILED, 5001, "해당 review에 대한 권한이 없습니다."),

    /**
     * 6000 : 서버 에러
     */
    SERVER_ERROR(FAILED, 6000, "서버 내부 에러."),
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
