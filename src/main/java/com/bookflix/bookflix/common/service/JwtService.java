package com.bookflix.bookflix.common.service;

import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.secret.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.jsonwebtoken.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.EMPTY_JWT;
import static com.bookflix.bookflix.common.response.BaseResponseStatus.INVALID_JWT;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JwtService {

    /*
    JWT 생성
    @param userIdx
    @return String
     */
    public String createJwt(Long userId, String socialType, String oauthId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userId", userId)
                .claim("socialType", socialType)
                .claim("oauthId", oauthId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365)))
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     */
    public Long getUserIdx() throws BaseException {

        //1. JWT 추출
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0)
            throw new BaseException(EMPTY_JWT);

        // 2. JWT parsing
        Jws<Claims> claims = Jwts.parser()
                                    .setSigningKey(Secret.JWT_SECRET_KEY)
                                    .parseClaimsJws(accessToken);

        // 3. userIdx 추출
        return claims.getBody().get("userId", Long.class);
    }

    public String getOauthChannel() throws BaseException {

        //1. JWT 추출
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0)
            throw new BaseException(EMPTY_JWT);

        // 2. JWT parsing
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Secret.JWT_SECRET_KEY)
                .parseClaimsJws(accessToken);

        // 3. oauth_channel 추출
        return claims.getBody().get("socialType", String.class);  // jwt 에서 userIdx를 추출합니다.
    }

    public String getOauthId() throws BaseException {

        //1. JWT 추출
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0)
            throw new BaseException(EMPTY_JWT);

        // 2. JWT parsing
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Secret.JWT_SECRET_KEY)
                .parseClaimsJws(accessToken);

        // 3. oauth_id 추출
        return claims.getBody().get("oauthId", String.class);  // jwt 에서 userIdx를 추출합니다.
    }
}
