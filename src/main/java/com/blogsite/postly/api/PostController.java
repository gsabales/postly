package com.blogsite.postly.api;

import com.blogsite.postly.model.rest.PostRequest;
import com.blogsite.postly.model.rest.PostResponse;
import com.blogsite.postly.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/postly/users")
@RequiredArgsConstructor
public class PostController
{
    private final PostService postService;

    @GetMapping(value = "/{userId}/post", produces = {APPLICATION_JSON_VALUE})
    public  ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable Long userId) throws RuntimeException
    {
        return ResponseEntity.ok().body(postService.getAllPosts(userId));
    }

    @GetMapping(value = "/{userId}/post/{postId}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> getSinglePost(@PathVariable Long userId,
           @PathVariable Long postId) throws RuntimeException
    {
        return ResponseEntity.ok().body(postService.getSinglePost(userId, postId));
    }

    @PostMapping(value = "/{userId}/post", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest,
           @PathVariable Long userId) throws RuntimeException
    {
        return ResponseEntity.ok().body(postService.createPost(postRequest, userId));
    }

    @PutMapping(value = "/{userId}/post/{postId}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest,
           @PathVariable Long userId, @PathVariable Long postId) throws RuntimeException
    {
        return ResponseEntity.ok().body(postService.updatePost(postRequest, userId, postId));
    }

    @DeleteMapping(value = "/{userId}/post/{postId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId,
           @PathVariable Long postId) throws RuntimeException
    {
        String message = postService.deletePostById(userId, postId);
        return ResponseEntity.ok().body(message);
    }
}
