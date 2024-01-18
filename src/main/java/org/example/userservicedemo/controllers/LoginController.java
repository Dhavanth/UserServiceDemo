package org.example.userservicedemo.controllers;

import org.example.userservicedemo.services.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private LoginService loginService;
    @PostMapping()
    public String login(@RequestBody String username, @RequestBody String password){
        return loginService.login(username, password);
    }
}
