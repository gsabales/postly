package com.blogsite.postly.service;

import com.blogsite.postly.model.Users;
import com.blogsite.postly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param id
     * @return specific user by id
     */
    public Users getUserById(int id)
    {
        return userRepository.findUserById(id);
    }

    /**
     * Add user in User database
     *
     * @param users
     * @return status smessage
     */
    public String addUser(Users users)
    {
        StringBuffer message = new StringBuffer();
        try
        {
            userRepository.save(users);
            message.append("Successfully added user: ")
                    .append(users.getFirstName()).append(" ")
                    .append(users.getLastName());
        }
        catch (Exception exception)
        {
            message.append("Error adding user: ")
                    .append(users.getFirstName()).append(" ")
                    .append(users.getLastName());
            message.append("Exception: ").append(exception.getMessage());
        }
        return message.toString();
    }

    /**
     * Update user by id
     *
     * @param restUsers
     * @param id
     * @return Updated user if successful
     */
    public Users updateUser(Users restUsers, int id)
    {
        try
        {
            Users domainUsers = userRepository.findUserById(id);
            if (restUsers != null && domainUsers != null)
            {
                domainUsers.setFirstName(restUsers.getFirstName());
                domainUsers.setLastName(restUsers.getLastName());
                domainUsers.setEmail(restUsers.getEmail());
                userRepository.save(domainUsers);
            }
        }
        catch (Exception exception)
        {
            log.error(exception.getMessage());
        }

        return userRepository.findUserById(id);
    }

    /**
     * Delete user by id
     *
     * @param Id
     * @return status message
     */
    public String deleteById(int Id)
    {
        StringBuffer message = new StringBuffer();
        try {
            userRepository.deleteById(Id);
            message.append("Successfully deleted user with ID: ").append(Id);
        }
        catch (Exception exception)
        {
            message.append("Error deleting user with ID: ").append(Id);
            message.append("Exception: ").append(exception.getMessage());
            log.error(exception.getMessage());
        }
        return message.toString();
    }
}
