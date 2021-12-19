package com.winble.server.influencer.domain.profile;

import javax.persistence.Embeddable;

@Embeddable
public class InfluencerInfo {
    private String cameraModel;         // 카메라 기종
    private String faceExposedCheck;    // 포스팅 얼굴 노출
    private String jointBolgCheck;      // 공동 블로구 유무
    private String skinType;            // 피부 타입

    public class InfluencerSize {
        private String topSize;         // 상의 사이즈
        private String bottomSize;      // 하의 사이즈
        private String shoeSize;        // 신발 사이즈
        private String height;          // 키
    }
}