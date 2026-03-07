package com.example.demo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IUserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleRepository roleRepository;

    private User buildValidUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword_hash("hashedpassword");
        user.setCreatedAt(Timestamp.valueOf("2023-01-01 00:00:00"));
        Role role = new Role();
        role.setName("Test Role");
        Role roleSaved = roleRepository.save(role);
        user.setRole(roleSaved);
        return user;
    }

    @Test
    public void testFindAll() {
        User user = buildValidUser("findalluser", "findalluser@example.com");
        userService.save(user);
        List<User> users = userService.findAll();
        assertEquals(1, users.size());
        assertNotNull(users);
        assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("findalluser")));
    }

    @Test
    public void testFindById() {
        User user = buildValidUser("testuser", "testuser@example.com");
        user = userService.save(user);

        User fetchedUser = userService.findById(user.getId());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(), fetchedUser.getId());
    }

    @Test
    public void testSave() {
        User user = buildValidUser("newuser", "newuser@example.com");
        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("newuser", savedUser.getUsername());
    }

    @Test
    public void testDeleteById() {
        User user = buildValidUser("todelete", "todelete@example.com");
        User retrievedUser = userService.save(user);

        userService.deleteById(retrievedUser.getId());
        assertThrows(RuntimeException.class, () -> userService.findById(retrievedUser.getId()));
    }

    @Test
    public void testFindAllPage() {
        User user1 = buildValidUser("pageduser1", "pageduser1@example.com");
        userService.save(user1);

        User user2 = buildValidUser("pageduser2", "pageduser2@example.com");
        userService.save(user2);

        List<User> users = userService.findAllPage(0, 1);
        assertNotNull(users);
        assertEquals(1, users.size());
    }

}
