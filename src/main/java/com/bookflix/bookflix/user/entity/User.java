package com.bookflix.bookflix.user.entity;

import com.bookflix.bookflix.common.entity.BaseTimeEntity;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.user.entity.enumType.Gender;
import com.bookflix.bookflix.user.entity.enumType.SocialType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String nickname;

    @Column(nullable = false)
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int region;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = false)
    private float latitude;

    private String inhaId;

    private String inhaPwd;

    @OneToMany(mappedBy = "user")
    private List<NearLibrary> libraryList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Recommend> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<History> historyList = new ArrayList<>();


    public static User createDefaultUser(String oauthId, SocialType socialType){
        return User.builder()
                .nickname("")
                .oauthId(oauthId)
                .socialType(socialType)
                .gender(Gender.MALE)
                .age(20)
                .region(11)
                .longitude(126.6535F)
                .latitude(37.4500F)
                .inhaId("")
                .inhaPwd("")
                .build();
    }

    @Builder
    public User(String nickname, String oauthId, SocialType socialType, Gender gender, int age,
                int region, float longitude, float latitude, String inhaId, String inhaPwd){
        this.nickname = nickname;
        this.oauthId = oauthId;
        this.socialType = socialType;
        this.gender = gender;
        this.age = age;
        this.region = region;
        this.longitude = longitude;
        this.latitude = latitude;
        this.inhaId = inhaId;
        this.inhaPwd = inhaPwd;
        this.libraryList = new ArrayList<>();
        this.recommendList = new ArrayList<>();
        this.historyList = new ArrayList<>();
    }

    public void updateInfo(String nickName, Gender gender, int age, int region, float longitude, float latitude) {
        this.nickname = nickName;
        this.gender = gender;
        this.age = age;
        this.region = region;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void addNearLibrary(NearLibrary nearLibrary) {
        this.libraryList.add(nearLibrary);
    }

    public void addHistory(History history) {
        this.historyList.add(history);
    }

    public void clearNearLibrary(){
        this.libraryList.clear();
    }
}
