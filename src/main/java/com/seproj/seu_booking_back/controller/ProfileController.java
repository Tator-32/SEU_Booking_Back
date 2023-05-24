package com.seproj.seu_booking_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.seproj.seu_booking_back.entity.UserAccount;
import com.seproj.seu_booking_back.service.IUserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ProfileController {
    private IUserAccountService iUserAccountService;

    public ProfileController(IUserAccountService iUserAccountService) {
        this.iUserAccountService = iUserAccountService;
    }

    @PostMapping("/profile")
    public Map<String, Object> profileGet(@RequestBody Map data) {
        if (data.get("phoneNo") != null) {
            String phone = data.get("phoneNo").toString();
            System.out.println("Profile Offered by: " + phone);
            try {
                QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("realName", "userName", "emailAddr", "phoneNum", "birthDate").eq("phoneNum", phone);
                Map<String, Object> result = iUserAccountService.getMap(queryWrapper);
                if (result != null) {
                    return result;
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else if (data.get("mailAddr") != null) {
            String mail = data.get("mailAddr").toString();
            System.out.println("Profile Offered by: " + mail);
            try {
                QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("realName", "userName", "emailAddr", "phoneNum", "birthDate").eq("emailAddr", mail);
                Map<String, Object> result = iUserAccountService.getMap(queryWrapper);
                if (result != null) {
                    return result;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        Map<String, Object> result = null;
        return result;
    }

    @PostMapping("/profileChange")
    public Boolean profileChange(@RequestBody Map data) {
        UpdateWrapper<UserAccount> updateWrapper = new UpdateWrapper<>();
        //构造条件
        updateWrapper.eq("phoneNum", data.get("phoneNo").toString()).or().eq("emailAddr", data.get("mailAddr").toString())
                .setSql("realName = \'" + data.get("realName").toString() + "\'" +
                        ", userName = \'" + data.get("userName").toString() + "\'" +
                        ", emailAddr = \'" + data.get("mailAddr").toString() + "\'" +
                        ", phoneNum = \'" + data.get("phoneNo").toString() + "\'" +
                        ", birthDate = \'" + data.get("birthDate").toString() + "\'");
        return iUserAccountService.update(updateWrapper);
    }
}
