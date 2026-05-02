package com.blogsite.postly.model.mapper;

import com.blogsite.postly.model.domain.Post;
import com.blogsite.postly.model.domain.Users;
import com.blogsite.postly.model.rest.PostRequest;
import com.blogsite.postly.model.rest.PostResponse;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class PostMapper
{
    public Post createMapRestToDomain(PostRequest request, Users poster)
    {
        Post domain = new Post();

        domain.setPoster(poster);
        domain.setTitle(request.getTitle());
        domain.setContent(request.getContent());
        domain.setIsPublished(request.getIsPublished());
        domain.setCreatedAt(new Date());

        return domain;
    }

    public Post updateMapRestToDomain(PostRequest request, Post domain)
    {
        domain.setTitle(request.getTitle());
        domain.setContent(request.getContent());
        domain.setIsPublished(request.getIsPublished());
        domain.setUpdatedAt(new Date());

        return domain;
    }


    public PostResponse mapDomainToRest(Post domain, Long userId)
    {
        PostResponse response = new PostResponse();

        response.setId(domain.getId());
        response.setPosterId(userId);
        response.setTitle(domain.getTitle());
        response.setContent(domain.getContent());
        response.setIsPublished(domain.getIsPublished());
        response.setCreatedAt(formattedDate(domain.getCreatedAt()));
        response.setUpdatedAt(formattedDate(domain.getUpdatedAt()));

        return response;
    }

    private String formattedDate(Date date)
    {
        return date != null ? date.toInstant()
                .atZone(ZoneId.of("Asia/Manila"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
    }
}
