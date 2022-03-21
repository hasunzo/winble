package com.winble.server.influencer.service.socialProfile;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class KakaoAccount {

    private Profile profile;

    @Getter
    public static class Profile {
        @SerializedName("nickname")
        private String nickName;
        @SerializedName("profile_image_url")
        private String picture;
    }
}
