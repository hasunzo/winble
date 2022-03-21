package com.winble.server.influencer.domain.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Media {
    @Id
    @Column(name = "MEDIA_ID")
    private Long id;
    private MediaName mediaName;

    @Getter
    @RequiredArgsConstructor
    public enum MediaName {
        BLOG, INSTAGRAM, YOUTUBE;
    }
}
