package com.bookflix.bookflix.oauth.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginRes {

    private String accessToken;
    private Boolean isNew;
    private Long userId;
}
