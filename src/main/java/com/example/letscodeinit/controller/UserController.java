package com.example.letscodeinit.controller;

import com.example.letscodeinit.domain.Role;
import com.example.letscodeinit.domain.User;
import com.example.letscodeinit.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") // на уровне класса чтоб не подписывать в каждом методе /user
@PreAuthorize("hasAuthority('ADMIN')") // будет для каждого из методов проверять наличие прав
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")//чтоб идентификатор был через /
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values()); // получаем все значения
        return "userEdit";
    }
    @PostMapping
    public String userSave( // для сохранения нужны некоторые параметры пользователя
            @RequestParam String username, // имя пользователя
            @RequestParam Map<String, String> form, // список полей из формы
            @RequestParam("userId") User user // по нему получаем пользователя из бд
    ) {
        user.setUsername(username); // новое имя

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name) // из енама в строковый вид
                .collect(Collectors.toSet());

        user.getRoles().clear(); // чистим все роли юзера

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key)); // проверяем что форма содержит роли для пользователя
            }
        }

        userRepo.save(user); // сохраняем

        return "redirect:/user"; // редирект перенаправление
    }
}
