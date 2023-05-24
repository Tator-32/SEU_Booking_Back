package com.seproj.seu_booking_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seproj.seu_booking_back.entity.HotelInfo;
import com.seproj.seu_booking_back.entity.RoomInfo;
import com.seproj.seu_booking_back.service.IHotelInfoService;
import com.seproj.seu_booking_back.service.IRoomInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class HotelSearchController {
    private IHotelInfoService iHotelInfoService;
    private IRoomInfoService iRoomInfoService;

    public HotelSearchController(IHotelInfoService iHotelInfoService, IRoomInfoService iRoomInfoService) {
        this.iHotelInfoService = iHotelInfoService;
        this.iRoomInfoService = iRoomInfoService;
    }

    @PostMapping("/hotelsearch")
    public List<Map<String, Object>> hotelSearch(@RequestBody Map data)
    {
        String hotelVal = data.get("searchVal").toString();
        if(data.get("type").toString().equals("hotel_address")) {
            System.out.println("Hotel Search:" + hotelVal);
            hotelVal = pickLast(hotelVal);
            hotelVal = "%" + hotelVal + "%";
            QueryWrapper<HotelInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("hotel_name", "hotel_address",
                    "hotel_feature", "comment_level",
                    "comment_number", "comment_score",
                    "img_url1", "img_url2",
                    "img_url3", "img_url4",
                    "img_url5", "img_url6",
                    "img_url7").like(data.get("type").toString(), hotelVal);
            List<Map<String, Object>> result = iHotelInfoService.listMaps(queryWrapper);
            return result;
        } else {
            System.out.println("Hotel Search:" + hotelVal);
            QueryWrapper<RoomInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("hotel_name").like(data.get("type").toString(), hotelVal);
            List<Map<String, Object>> result = iRoomInfoService.listMaps(queryWrapper);
            List<Map<String,Object>> resultNext = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> item:result) {
                QueryWrapper<HotelInfo> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.select("hotel_name", "hotel_address",
                        "hotel_feature", "comment_level",
                        "comment_number", "comment_score",
                        "img_url1", "img_url2",
                        "img_url3", "img_url4",
                        "img_url5", "img_url6",
                        "img_url7").eq("hotel_name", item.get("hotel_name"));
                resultNext.add(iHotelInfoService.getMap(queryWrapper1));
            }
            System.out.println(resultNext);
            return resultNext;
        }
    }

    private String pickLast(String str) {
        int lastIndex = str.lastIndexOf(",");
        if (lastIndex != -1) {
            return str.substring(lastIndex + 2, str.length() - 1);
        } else {
            return null; // 没有逗号，返回null
        }
    }
}
