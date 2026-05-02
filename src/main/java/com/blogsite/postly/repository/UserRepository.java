package com.blogsite.postly.repository;

import com.blogsite.postly.model.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
