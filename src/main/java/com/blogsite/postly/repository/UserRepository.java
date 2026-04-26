package com.blogsite.postly.repository;

import com.blogsite.postly.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer>
{
    /**
     * Retrieve all users
     *
     * @return list of all users
     */
    List<Users> findAllByOrderByIdAsc();

    /**
     * Return user by id
     *
     * @param id
     * @return specific user by id
     */
    Users findUserById(int id);

    /**
     * Delete user by id
     *
     * @param id
     */
    void deleteById(int id);
}
