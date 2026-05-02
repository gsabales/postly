package com.blogsite.postly.model.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse
{
    private Long id;
    private Long posterId;
    private String title;
    private String content;
    private Boolean isPublished;
    private String createdAt;
    private String updatedAt;
}
