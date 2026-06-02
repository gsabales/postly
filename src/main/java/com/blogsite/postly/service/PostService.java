package com.blogsite.postly.service;

import com.blogsite.postly.model.domain.Post;
import com.blogsite.postly.model.domain.Users;
import com.blogsite.postly.model.mapper.PostMapper;
import com.blogsite.postly.model.rest.PostRequest;
import com.blogsite.postly.model.rest.PostResponse;
import com.blogsite.postly.repository.PostRepository;
import com.blogsite.postly.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Transactional
    public PostResponse createPost(PostRequest request, Long posterId) throws RuntimeException
    {
        if (request == null)
        {
            throw new IllegalArgumentException("Post is empty");
        }
        Users poster = userRepository.findById(posterId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + posterId));
        Post postDomain = postMapper.createMapRestToDomain(request, poster);
        Post createdPost = postRepository.save(postDomain);
        return postMapper.mapDomainToRest(createdPost, posterId);
    }

    @Transactional
    public PostResponse updatePost(PostRequest request, Long posterId, Long postId) throws RuntimeException
    {
        if (request == null)
        {
            throw new IllegalArgumentException("Post is empty");
        }
        Post postDomain = postRepository.findByIdAndPosterId(postId, posterId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found for this user: " + posterId));
        postDomain = postMapper.updateMapRestToDomain(request, postDomain);
        Post updatedPost = postRepository.save(postDomain);
        return postMapper.mapDomainToRest(updatedPost, posterId);
    }
}
