package com.winble.server.influencer.service;

import com.winble.server.exception.influencer.CEmailLoginFailedException;
import com.winble.server.exception.influencer.CInfluencerExistException;
import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.enumeration.InfluencerStatus;
import com.winble.server.influencer.domain.profile.BasicProfile;
import com.winble.server.influencer.service.socialProfile.SocialProfile;
import com.winble.server.influencer.web.rest.dto.request.InfluencerJoinRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerLoginRequest;
import com.winble.server.influencer.web.rest.dto.request.InfluencerSocialLoginRequest;
import com.winble.server.common.jwt.JwtTokenProvider;
import com.winble.server.influencer.domain.enumeration.Role;
import com.winble.server.influencer.domain.enumeration.SignUpType;
import com.winble.server.influencer.repository.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignService {

    private final InfluencerRepository influencerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final SocialService socialService;


    /**
     *
     * 자사 서비스 로그인
     * 아이디와 비밀번호 확인 과정에 성공하면
     * 리소스에 접근할 수 있는 토큰이 반환된다.
     *
     * @param influencerLoginRequest
     * @return
     */
    public String login(InfluencerLoginRequest influencerLoginRequest) {
        // 이메일(loginId)로 회원을 찾는다.
        Influencer influencer = influencerRepository.findByLoginId(influencerLoginRequest.getLoginId()).
                orElseThrow(CEmailLoginFailedException::new);

        // 비밀번호가 일치하는 지 확인한다.
        if (!passwordEncoder.matches(influencerLoginRequest.getPassword(), influencer.getPassword())) {
            throw new CEmailLoginFailedException();
        }

        // 토큰을 발급한다.
        return jwtTokenProvider.createToken(String.valueOf(influencer.getId()));
    }

    /**
     *
     * 자사 서비스 회원가입
     *
     * @param influencerJoinRequest
     */
    public void signUp(InfluencerJoinRequest influencerJoinRequest) {
        // 존재 여부를 확인한다.
        isPresentUser(influencerJoinRequest.getLoginId());

        // 회원을 생성한다.
        BasicProfile basicProfile = new BasicProfile(influencerJoinRequest.getNickName());

        influencerRepository.save(Influencer.builder()
                .loginId(influencerJoinRequest.getLoginId())
                .password(passwordEncoder.encode(influencerJoinRequest.getPassword()))
                .basicProfile(basicProfile)
                .signUpType(SignUpType.WINBLE)
                .role(Role.USER)
                .status(InfluencerStatus.ACTIVE)
                .build());
    }

    /**
     *
     * 소셜 서비스 로그인 및 회원가입
     *
     * @param influencerSocialLoginRequest
     * @return
     */
    public String socialLoginAndSignUp(InfluencerSocialLoginRequest influencerSocialLoginRequest) {
        // 소셜 인가코드와 가입 유형으로 소셜 회원의 프로필을 가져온다.
        SocialProfile socialProfile = socialService.getSocialProfile(
                influencerSocialLoginRequest.getAccessToken(),
                influencerSocialLoginRequest.getSignUpType()
        );

        // 로그인 아이디로 회원을 찾고 존재하지 않는다면 회원으로 등록한다.
        Influencer influencer = influencerRepository
                .findByLoginIdAndSignUpType(socialProfile.getLoginId(), SignUpType.valueOf(influencerSocialLoginRequest.getSignUpType()))
                .orElse(influencerRepository.save(socialProfile.toInfluencer()));

        // 토큰을 발급하여 반환한다.
        return jwtTokenProvider.createToken(String.valueOf(influencer.getId()));
    }

    // 가입 유무를 확인하는 메소드
    // 가입된 회원이라면 예외 처리된다.
    public void isPresentUser(String loginId) {
        Optional<Influencer> influencer = influencerRepository.findByLoginId(loginId);
        if (influencer.isPresent()) {
            throw new CInfluencerExistException();
        }
    }
}
