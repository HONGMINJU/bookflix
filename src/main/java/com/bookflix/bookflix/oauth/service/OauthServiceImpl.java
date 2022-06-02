package com.bookflix.bookflix.oauth.service;

import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.oauth.dto.response.LoginRes;
import com.bookflix.bookflix.user.entity.enumType.SocialType;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OauthServiceImpl implements OauthService{

    private final UserRepository userRepository;

    private final OauthServiceUtil oauthServiceUtil;

    private final JwtService jwtService;

    @Override
    public LoginRes socialLogin(String socialType, String accessToken) {

        String oauthId = "";
        String jwt = "";
        Boolean isNew = true;
        Long userId = 0L;
        SocialType socialTypeEnum = null;
        LoginRes loginRes = null;

        if (socialType == "kakao") {
            oauthId = oauthServiceUtil.getKakaoOauthId(accessToken);
            socialTypeEnum = SocialType.KAKAO;
        }
        else if(socialType == "naver") {
            oauthId = oauthServiceUtil.getNaverOauthId(accessToken);
            socialTypeEnum = SocialType.NAVER;
        }

        if (userRepository.existsBySocialTypeAndOauthId(socialTypeEnum, oauthId)) {

            Optional<User> user = userRepository.findUserBySocialTypeAndOauthId(socialTypeEnum, oauthId);
            String nickname = user.get().getNickname();
            userId = user.get().getId();
            jwt = jwtService.createJwt(userId, socialType, oauthId);

            if (nickname.equals(""))
                isNew = true;
            else
                isNew = false;
        }
        else {
            //db에 oauthid 존재하지 않는경우 디비에 삽입하고 리턴
            User user = userRepository.save(new User(oauthId, socialTypeEnum));
            jwt = jwtService.createJwt(user.getId(), socialType, oauthId);
            isNew = false;
            userId = user.getId();
        }
        return new LoginRes(jwt, isNew, userId);
    }
}
