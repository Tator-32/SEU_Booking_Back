package com.seproj.seu_booking_back.controller;//package com.eaproj.seu_booking_project.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AccountController {

    @PostMapping("/account")
    public boolean Register(@RequestBody Map data) {
        String userName = data.get("user_address").toString();
        System.out.print(userName);
        if(userName.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
}
