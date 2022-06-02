package com.bookflix.bookflix.user.dto.response;

import com.bookflix.bookflix.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUserRes {

    private Long userId;

    @Builder
    public PostUserRes(Long userId) {
        this.userId = userId;
    }

    public static PostUserRes of(User user){
        return PostUserRes.builder()
                .userId(user.getId())
                .build();
    }
}
