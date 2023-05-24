package com.seproj.seu_booking_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seproj.seu_booking_back.entity.UserAccount;
import com.seproj.seu_booking_back.entity.UserOrder;
import com.seproj.seu_booking_back.service.IUserAccountService;
import com.seproj.seu_booking_back.service.IUserOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class UserOrderController {
    private IUserOrderService iUserOrderService;
    private IUserAccountService iUserAccountService;

    public UserOrderController(IUserOrderService iUserOrderService, IUserAccountService iUserAccountService) {
        this.iUserOrderService = iUserOrderService;
        this.iUserAccountService = iUserAccountService;
    }

    @PostMapping("/userorder")
    public List<Map<String, Object>> OrderGet(@RequestBody Map data) {
        try{
            QueryWrapper<UserAccount> accountQueryWrapper = new QueryWrapper<>();
            accountQueryWrapper.select("ID")
                    .eq("phoneNum", data.get("login").toString())
                    .or()
                    .eq("emailAddr", data.get("login").toString());
            Map<String, Object> result = iUserAccountService.getMap(accountQueryWrapper);
            if(result != null) {
                QueryWrapper<UserOrder> UserOrderQueryWrapper = new QueryWrapper<>();
                UserOrderQueryWrapper.select("hotel","room_type","timeIn","timeOut").eq("ID", result.get("ID").toString());
                List<Map<String, Object>> resultNext = iUserOrderService.listMaps(UserOrderQueryWrapper);
                if(resultNext != null) {
                    return resultNext;
                }
            }
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    @PostMapping("/cancelorder")
    public Boolean OrderCancel(@RequestBody Map data) {
        try {

        } catch (Exception e) {System.out.println(e);}
        return false;
    }
}
