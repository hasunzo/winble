package com.winble.server.influencer.domain.profile;

import com.winble.server.influencer.domain.Influencer;
import com.winble.server.influencer.domain.profile.idclass.InfluencerMediaId;

import javax.persistence.*;

@Entity
@IdClass(InfluencerMediaId.class)
public class InfluencerMedia {

    @Id
    @ManyToOne
    @JoinColumn(name = "INFLUENCER_ID")
    private Influencer influencer;

    @Id
    @ManyToOne
    @JoinColumn(name = "MEDIA_ID")
    private Media media;

    private String mediaURL;
}
