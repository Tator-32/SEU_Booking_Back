package com.seproj.seu_booking_back.controller;//package com.eaproj.seu_booking_project.controller;

import com.seproj.seu_booking_back.service.SendSms;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AccountController {

    @PostMapping("/account")
    public String Register(@RequestBody Map data) {
        SecureRandom randomGen = new SecureRandom();
        String code = String.format("%04d", randomGen.nextInt(1000));
        String loginVal = data.get("loginVal").toString();
        String loginType = data.get("loginType").toString();
        System.out.print(loginVal + ", " + loginType + '\n');

        SendSms messageSender = new SendSms(loginVal, code);
        return loginVal + ", " + loginType;
    }
}
