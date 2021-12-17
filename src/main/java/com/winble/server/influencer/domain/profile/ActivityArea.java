package com.winble.server.influencer.domain.profile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * 활동 지역 엔티티
 *
 */
@Entity
public class ActivityArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITYAREA_ID")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String activityAreaName;
}
