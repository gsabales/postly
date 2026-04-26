package com.blogsite.postly.api;

import com.blogsite.postly.model.Users;
import com.blogsite.postly.service.UserService;
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
@RequestMapping("/api/postly")
@RequiredArgsConstructor
public class PostlyController
{
    private final UserService userService;

    @GetMapping(value = "/users", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Users>> getAllUsers()
    {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/users/{id}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> getUserById(@PathVariable int id)
    {
        Users user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/users", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addUser(@RequestBody Users user)
    {
        String message = userService.addUser(user);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping(value = "/users/{id}", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable int id)
    {
        Users userBody = userService.updateUser(user, id);
        return ResponseEntity.ok().body(userBody);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable int id)
    {
        String message = userService.deleteById(id);
        return ResponseEntity.ok().body(message);
    }
}
