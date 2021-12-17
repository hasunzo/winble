package com.winble.server.influencer.domain.profile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * 블로그 활동 주제 (블로그 카테고리) 엔티티 *
 */
@Entity
public class BlogCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOGCATEGORY_ID")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String blogCategoryName;
}
