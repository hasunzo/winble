package com.winble.server.influencer.domain.profile.idclass;

import java.io.Serializable;
import java.util.Objects;

public class InfluencerMediaId implements Serializable {

    private String influencer;
    private String media;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerMediaId that = (InfluencerMediaId) o;
        return Objects.equals(influencer, that.influencer) && Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(influencer, media);
    }
}
