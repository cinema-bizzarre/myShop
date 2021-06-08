package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    @Secured({"ADMIN"})
    @GetMapping({"/all_users", "/all_users/{pageId}"})
    public String getUserList(Model model, @PathVariable(required = false) Integer pageId) {
        if (pageId == null) {
            pageId = 1;
        }
        PageRequest pageRequest = PageRequest.of(pageId - 1, 10);
        final Page<User> users = userService.findAll(pageRequest);
        model.addAttribute("users", users.getContent());
        final int pageNumber = users.getPageable().getPageNumber();
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("previousPage", users.getPageable().hasPrevious() ? pageNumber : null);
        model.addAttribute("nextPage", users.getTotalPages() > pageNumber + 1 ? pageNumber + 2 : null);
        return "all_users";
    }
}
