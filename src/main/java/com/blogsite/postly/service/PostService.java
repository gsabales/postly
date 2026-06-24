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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    /**
     * Get all posts of the original poster
     *
     * @param posterId
     * @return
     */
    public List<PostResponse> getAllPosts(Long posterId)
    {
        Users poster = userRepository.findById(posterId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + posterId));
        return postRepository.findAllByPosterId(poster.getId()).stream()
                .map(post -> postMapper.mapDomainToRest(post, posterId))
                .toList();
    }

    /**
     * Get one post of the original poster
     *
     * @param posterId
     * @param postId
     * @return
     */
    public PostResponse getSinglePost(Long posterId, Long postId)
    {
        return postRepository.findByIdAndPosterId(postId, posterId)
                .map(post -> postMapper.mapDomainToRest(post, posterId))
                .orElseThrow(() -> new RuntimeException("Post " + postId + "not found for this user: " + posterId));
    }

    /**
     * Create new post based from client request and poster id
     *
     * @param request
     * @param posterId
     * @return post response
     * @throws RuntimeException
     */
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

    /**
     * Update post based from client request, poster id, and post id
     *
     * @param request
     * @param posterId
     * @return post response
     * @throws RuntimeException
     */
    @Transactional
    public PostResponse updatePost(PostRequest request, Long posterId, Long postId) throws RuntimeException
    {
        if (request == null)
        {
            throw new IllegalArgumentException("Post is empty");
        }
        Post postDomain = postRepository.findByIdAndPosterId(postId, posterId)
                .orElseThrow(() -> new IllegalArgumentException("Post " + postId + "not found for this user: " + posterId));
        postDomain = postMapper.updateMapRestToDomain(request, postDomain);
        Post updatedPost = postRepository.save(postDomain);
        return postMapper.mapDomainToRest(updatedPost, posterId);
    }

    /**
     * Delete post by id
     *
     * @param postId
     * @return status message
     * @throws RuntimeException
     */
    @Transactional
    public String deletePostById(Long posterId, Long postId) throws RuntimeException
    {
        Optional<Post> optPost = postRepository.findByIdAndPosterId(postId, posterId);
        if (optPost.isPresent())
        {
            Post post = optPost.get();
            postRepository.delete(post);
            return "Successfully deleted post with id: " + postId;
        }
        else
        {
            throw new RuntimeException("Post " + postId + "not found for this user: " + posterId);
        }
    }
}
