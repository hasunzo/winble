package com.winble.server.influencer.domain.profile;

import com.winble.server.influencer.domain.Influencer;

import javax.persistence.*;

@Entity
public class InfluencerMedia {
    @Id
    @Column(name = "INFLUENCER_MEDIA_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "INFLUENCER_ID")
    private Influencer influencer;

    @ManyToOne
    @JoinColumn(name = "MEDIA_ID")
    private Media media;

    private String mediaURL;
}
