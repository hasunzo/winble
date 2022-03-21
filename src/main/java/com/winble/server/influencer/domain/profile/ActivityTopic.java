package com.winble.server.influencer.domain.profile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * 활동 주제 엔티티
 *
 */
@Entity
public class ActivityTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITYTOPIC_ID")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String topicName;
}
