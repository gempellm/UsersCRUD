package com.gempel.app.rest.Controller;


import com.gempel.app.rest.Models.User;
import com.gempel.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome!";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/search/{id}")
    public Optional<User> searchUser(@PathVariable long id) {
        return userRepo.findById(id);
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User updatedUser = optional.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            updatedUser.setOccupation(user.getOccupation());
            userRepo.save(updatedUser);
            return "Updated...";
        } else {
            return "No such user found!";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User deleteUser = optional.get();
            userRepo.delete(deleteUser);
            return "Delete user with the id: " + id;
        } else {
            return "No such user found!";
        }
    }

}
