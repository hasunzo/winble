package com.winble.server.influencer.service;

import com.google.gson.Gson;
import com.winble.server.exception.social.CCommunicationException;
import com.winble.server.exception.social.CProviderNotFoundException;
import com.winble.server.influencer.service.socialProfile.KakaoProfile;
import com.winble.server.influencer.service.socialProfile.NaverProfile;
import com.winble.server.influencer.service.socialProfile.SocialProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class SocialService {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;

    public SocialProfile getSocialProfile(String accessToken, String socialType) {
        // Set Header : Content-type : application/x-www.form-urlencoded
        // 헤더에 accessToken 담기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer "+accessToken);

        String requestUrl = getRequestUrl(socialType);

        // Set http entity
        try {
            // Request profile
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(
                    requestUrl, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return getMemberInfoResponse(response.getBody(), socialType);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CCommunicationException();
        }
        throw new CCommunicationException();
    }

    private SocialProfile getMemberInfoResponse(String response, String socialType) {
        switch (socialType) {
            // 카카오
            case "KAKAO":
                return gson.fromJson(response, KakaoProfile.class);
            case "NAVER":
                return gson.fromJson(response, NaverProfile.class);
            default:
                // 적절하지 않은 provider
                throw new CProviderNotFoundException();
        }
    }

    // provider에 따라 요청 주소 설정하기
    private String getRequestUrl(String socialType) {
        String requestUrl = "";
        switch (socialType) {
            // 카카오
            case "KAKAO":
                requestUrl = "https://kapi.kakao.com/v2/user/me";
                break;
            // 네이버
            case "NAVER":
                requestUrl = "https://openapi.naver.com/v1/nid/me";
                break;
            default:
                // 적절하지 않은 provider
                throw new CProviderNotFoundException();
        }
        return requestUrl;
    }
}
