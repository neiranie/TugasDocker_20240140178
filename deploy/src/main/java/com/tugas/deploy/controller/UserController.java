package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<>();

    // Login
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        if ("admin".equals(username) && "20240140178".equals(password)) {
            return "redirect:/home";
        }
        return "login";
    }

    // Home
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", users);
        return "home";
    }

    // Form tambah
    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/form")
    public String addUser(@RequestParam String nama,
                          @RequestParam String nim,
                          @RequestParam String jenisKelamin) {
        users.add(new User(nama, nim, jenisKelamin));
        return "redirect:/home";
    }

    // Edit
    @GetMapping("/edit/{nim}")
    public String editPage(@PathVariable String nim, Model model) {
        for (User u : users) {
            if (u.getNim().equals(nim)) {
                model.addAttribute("user", u);
                break;
            }
        }
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam String nama,
                           @RequestParam String nim,
                           @RequestParam String jenisKelamin) {
        for (User u : users) {
            if (u.getNim().equals(nim)) {
                u.setNama(nama);
                u.setJenisKelamin(jenisKelamin);
                break;
            }
        }
        return "redirect:/home";
    }

    // Delete
    @GetMapping("/delete/{nim}")
    public String deleteUser(@PathVariable String nim) {
        users.removeIf(u -> u.getNim().equals(nim));
        return "redirect:/home";
    }

    // Logout
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}