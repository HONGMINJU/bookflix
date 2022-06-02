package com.bookflix.bookflix.user.dto.request;

import com.bookflix.bookflix.user.entity.enumType.Gender;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PutUserReq {

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PositiveOrZero
    private int age;

    @PositiveOrZero
    private int region;

    private float longitude;

    private float latitude;
}
