package com.careplus.userservice.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/ping")
    public String ping() { return "user-service: OK"; }
}
