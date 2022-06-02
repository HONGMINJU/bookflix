package com.bookflix.bookflix.user.dto.response;

import com.bookflix.bookflix.user.entity.enumType.Gender;
import com.bookflix.bookflix.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetUser {
    /*
    	"nickname" : String,
		"gender" : String,
		"age" : int,
		"region" : int,
		"inhaConnect" : boolean //인하대 연동 여부
     */
    private String nickname;
    private Gender gender;
    private int age;
    private int region;
    private boolean inhaConnect;

    @Builder
    public GetUser(String nickname, Gender gender, int age, int region, boolean inhaConnect) {
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.region = region;
        this.inhaConnect = inhaConnect;
    }

    public static GetUser of(User user){
        boolean inhaConnect = (user.getInhaId().equals(""));
        return GetUser.builder()
                .nickname(user.getNickname())
                .gender(user.getGender())
                .age(user.getAge())
                .region(user.getRegion())
                .inhaConnect(inhaConnect)
                .build();
    }
}
