package com.blogsite.postly.model.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest
{
    private Long id;
    private String title;
    private String content;
    private Boolean isPublished;
}
