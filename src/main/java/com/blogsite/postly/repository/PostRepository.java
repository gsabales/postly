package com.blogsite.postly.repository;

import com.blogsite.postly.model.domain.Post;;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>
{
    Optional<Post> findByIdAndPosterId(Long id, Long posterId);
}
