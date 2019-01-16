package edu.palevobot.controllers;

import edu.palevobot.entities.User;
import edu.palevobot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

   /* @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }*/
    @GetMapping("/{username}")
    public User getUserById(@PathVariable(value = "username")  String username) {
        for (User user: userRepository.findAll()) {
            if(user.getUsername().trim().equals(username.trim()) || user.getUsername().equals(username))
                return user;
        }
        throw new IllegalArgumentException("Wrong username");
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable(value = "username") String username,
                              @Valid @RequestBody User userDetails) {

        User user = null;
        for (User us: userRepository.findAll()
             ) {
            if(us.getUsername().equals(username))
                user = us;
        }
        if(user == null)
            throw new IllegalArgumentException("Wrong username");

        user.setUsername(userDetails.getUsername());
        user.setRating(userDetails.getRating());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "username") String username) {
        User user = null;
        for (User us: userRepository.findAll()) {
            if(us.getUsername().equals(username))
                user = us;
        }
        if(user == null)
            throw new IllegalArgumentException("Wrong username");

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
