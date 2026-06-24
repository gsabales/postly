package com.blogsite.postly.repository;

import com.blogsite.postly.model.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>
{
    /**
     * Retrieve all users
     *
     * @return list of all users
     */
    List<Users> findAllByOrderByIdAsc();

    /**
     * Delete user by id
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Retrieve user by email (username)
     *
     * @param email
     * @return retrieved user
     */
    Optional<Users> findByEmail(String email);
}
