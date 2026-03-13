package com.example.demo.controller.mvc;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mvc/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "users/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userService.save(user);
        return "redirect:/mvc/users";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("actualUser", user);
        model.addAttribute("roles", roleService.findAll());
        return "users/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("actualUser") User user) {
        userService.save(user);
        return "redirect:/mvc/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/mvc/users";
    }
}
