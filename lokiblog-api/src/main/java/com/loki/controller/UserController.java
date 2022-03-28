package com.loki.controller;


import com.loki.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;


}