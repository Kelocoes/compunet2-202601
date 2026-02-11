package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.User;

public class UserRepository {
    
    private List<User> users;

    public void init() {
        this.users = new ArrayList<>();
        this.users.add(createUser(1, "jdoe", "John", "Doe", 30));
        this.users.add(createUser(2, "asmith", "Alice", "Smith", 25));
    }

    private User createUser(Integer id, String username, String name, String lastname, Integer age) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);
        return user;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void destroy() {
        this.users = null;
    }

    public Optional<User> findUserByUsername(String username) {
        return this.users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
    }

    public User saveUser(User user) {
        User newUser = new User();
        newUser.setId(this.users.size() + 1);
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        this.users.add(newUser);
        return newUser;
    }
}
