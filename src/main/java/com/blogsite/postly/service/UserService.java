package com.blogsite.postly.service;

import com.blogsite.postly.model.domain.Users;
import com.blogsite.postly.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    /**
     * Retrieve all users in ascending order
     *
     * @return list of all users
     */
    public List<Users> getAllUsers()
    {
        return userRepository.findAllByOrderByIdAsc();
    }

    /**
     * Return user by id
     *
     * @param userId
     * @return specific user by id
     */
    public Users getUserById(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    /**
     * Add user in User database
     *
     * @param restUser
     * @return status smessage
     */
    @Transactional
    public Users addUser(Users restUser) throws IllegalArgumentException
    {
        if (restUser == null)
        {
            throw new IllegalArgumentException("User is empty");
        }
        return userRepository.save(restUser);
    }

    /**
     * Update user by id
     *
     * @param restUser
     * @param userId
     * @return Updated user if successful
     */
    @Transactional
    public Users updateUser(Users restUser, Long userId) throws RuntimeException
    {
        if (restUser == null)
        {
            throw new IllegalArgumentException("No user exists with id: " + userId);
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return userRepository.save(user);
    }

    /**
     * Delete user by id
     *
     * @param Id
     * @return status message
     */
    @Transactional
    public String deleteUserById(Long userId) throws RuntimeException
    {
        Optional<Users> optUser = userRepository.findById(userId);
        if (optUser.isPresent())
        {
            Users user = optUser.get();
            userRepository.delete(user);
            return "Successfully removed user with id: " + userId;
        }
        else
        {
            throw new RuntimeException("No user exists with id: " + userId);
        }
    }
}
