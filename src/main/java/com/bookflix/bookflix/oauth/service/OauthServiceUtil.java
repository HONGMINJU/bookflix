package com.bookflix.bookflix.oauth.service;

import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OauthServiceUtil {

    private static final String kakaoURL = "https://kapi.kakao.com/v2/user/me";
    private static final String naverURL = "https://openapi.naver.com/v1/nid/me";

    public String getKakaoOauthId(String accessToken) {
        int id = 0;
        try {
            URL url = new URL(kakaoURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response body : " + result);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parseString(result);

            id = element.getAsJsonObject().get("id").getAsInt();
        } catch (Exception e) { //
            throw new BaseException(BaseResponseStatus.POST_USERS_KAKAO_ERROR);
        }
        return Integer.toString(id);
    }

    public String getNaverOauthId(String accessToken){
        String header = "Bearer " + accessToken;
        String id = "";
        try {
            URL url = new URL(naverURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                throw new BaseException(BaseResponseStatus.POST_USERS_NAVER_ERROR);
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parseString(response.toString());

            id = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.POST_USERS_NAVER_ERROR);
        }
        return id;
    }
}
