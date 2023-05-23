package com.seproj.seu_booking_back.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
@CrossOrigin
@RequestMapping("/")
public class ThirdPageController {

    @PostMapping("/thirdPage")
    public Map init(@RequestBody Map data) {


        // 构造ResponseData对象
        List<Map<String, String>> rules = new ArrayList<>();

        String[] ruleHeads = {"你不许参加apex竞赛标题", "你可以参加apex竞赛标题","这是一个后端数据标题"};
        String[] ruleContents = {"你不许参加apex竞赛内容", "你可以参加apex竞赛内容","这是一个后端数据内容"};
        for (int i = 0; i < ruleHeads.length; i++) {
            Map<String, String> rule = new HashMap<>();
            rule.put("head", ruleHeads[i]);
            rule.put("content", ruleContents[i]);
            rules.add(rule);
        }

        List<String>  image_url_list=new ArrayList<>();

        String list[]={
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/170758821.jpg?k=9718ef0be2e07d96c6f510bc4d356aa5ee0b15a268e43d4b6427a454dcc86d52&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/170758784.jpg?k=aaa9e9ea92b9dfffdb4fa3bae3379908de5a19cf5ed6b444b0c3354c34215c19&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/214301339.jpg?k=e22b97d15e94a1520601a87e05db4a3a54cfb989afe986639d0758ba0c3b7325&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/214301416.jpg?k=49858b10803e9020f0a92971d3915878f4f8e6f4cef00af4088343455f2330fe&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/173422707.jpg?k=a2de862eb16c63bed2a855a7466675c6a5bc15102f177bdb0c4564678b254254&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/173330732.jpg?k=1c0fcae3d70f882c33928458189926fb76b639310383ad6c5ce9e5963aabe015&o=&hp=1",
                "https://ac-a.static.booking.cn/xdata/images/hotel/max1024x768/214301262.jpg?k=ae0c61321ac62be2a0fcffc49f711e17aea58dd5eb5765acdcd978c6ed1ccc0f&o=&hp=1"
        };
        for(int i=0;i<list.length;i++)
        {
            image_url_list.add(list[i]);
        }



        String hotel_name="后端酒店名称";



        String hotel_massage="后端酒店简介";



        int percentage[]={100, 90, 80, 70, 60, 50, 40, 30, 10};



        List<Map<String, Object>> tableData = new ArrayList<>();
        Map<String, Object> Data=new HashMap<>();
        Map<String, String> type = new HashMap<>();
        type.put("name_room","公寓 A");
        type.put("bed","1张超大号双人床");
        type.put("info","这间双人间配有平板电视、浴袍和沙发");
        Data.put("type",type);
        Data.put("price",1000);
        Data.put("nknow","须知 A");
        Data.put("isBookable",true);
        tableData.add(Data);

        type = new HashMap<>();
        Data=new HashMap<>();
        type.put("name_room","公寓 B");
        type.put("bed","1张超大号双人床");
        type.put("info","这间双人间配有平板电视、浴袍和沙发。");
        Data.put("type",type);
        Data.put("price",1000);
        Data.put("nknow","须知 B");
        Data.put("isBookable",true);
        tableData.add(Data);

        type = new HashMap<>();
        Data=new HashMap<>();
        type.put("name_room","公寓 C");
        type.put("bed","1张超大号双人床");
        type.put("info","这间双人间配有平板电视、浴袍和沙发。");
        Data.put("type",type);
        Data.put("price",1000);
        Data.put("nknow","须知 C");
        Data.put("isBookable",true);
        tableData.add(Data);


        List<String> character1=new ArrayList<>();
        character1.add("aaa");
        character1.add("bbb");
        character1.add("ccc");
        List<String> character2=new ArrayList<>();
        character2.add("ddd");
        character2.add("eee");
        character2.add("fff");
        character2.add("ggg");

        List<List<String>> facility=new ArrayList<>();

        List<String> fac=new ArrayList<>();
        fac.add("aaa");
        fac.add("ee");
        fac.add("bbb");
        fac.add("ccc");
        facility.add(fac);


        fac=new ArrayList<>();
        fac.add("户外家具");
        fac.add("露台");
        fac.add("花园");
        facility.add(fac);


        fac=new ArrayList<>();
        fac.add("客人可携带宠物入住。 住宿方可能会收取额外费用。");
        facility.add(fac);


        fac=new ArrayList<>();
        fac.add("平板电视");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("住宿内咖啡店");
        fac.add("小吃吧");
        fac.add("酒吧");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("住宿方于各处提供WiFi（免费）。");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add(" 无需预订：住宿场所设有私人停车设施，收费标准：每日€ 20。");
        fac.add("停车库");
        fac.add("无障碍停车场");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("杂货递送");
        fac.add("每日客房打扫");
        fac.add("共用休息室/电视区");
        fac.add( "办理私人登记入住/退房手续");
        fac.add("行李寄存");
        fac.add("额外收费");
        fac.add("传真/复印");
        fac.add("快速办理入住/退房手续");
        fac.add("24小时前台");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("提供发票");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("灭火器");
        fac.add("住宿外部闭路电视监控系统");
        fac.add("公共区域闭路电视监控系统");
        fac.add("烟雾报警器");
        fac.add("安全报警器");
        fac.add("钥匙进门");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("吸烟区");
        fac.add("空调");
        fac.add("酒店各处禁烟");
        fac.add("暖气");
        fac.add("隔音");
        fac.add("隔音客房");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("盥洗盆较低");
        fac.add("坐便器较高");
        fac.add("坐便器 - 带扶手");
        fac.add("无障碍通道");
        facility.add(fac);

        fac=new ArrayList<>();
        fac.add("阿拉伯语");
        fac.add("英语");
        fac.add("西班牙语");
        fac.add("法语");
        fac.add("印地语");
        facility.add(fac);



        List<Map<String,String>> comments=new ArrayList<>();
        Map<String,String> comment=new HashMap<>();
        comment.put("imgSrc","https://tse3-mm.cn.bing.net/th/id/OIP-C.Fc_sZTmq-1Q8aKpCX7ewMgAAAA?w=210&h=210&c=7&r=0&o=5&dpr=1.3&pid=1.7");
        comment.put("text","花开富贵");
        comment.put("comment","我们在这家酒店住了几晚，整个住宿过程非常愉快。员工非常友好，房间干净整洁，早餐也非常美味。");
        comments.add(comment);

        comment=new HashMap<>();
        comment.put("imgSrc","https://tse4-mm.cn.bing.net/th/id/OIP-C.gV9xEU5BrP0QQTzLJzmoSAAAAA?w=210&h=210&c=7&r=0&o=5&dpr=1.3&pid=1.7");
        comment.put("text","大漠祥云");
        comment.put("comment","这家酒店的设施非常好，位置便捷，员工热情周到。房间宽敞舒适，床铺非常柔软舒适，也很安静。");
        comments.add(comment);

        comment=new HashMap<>();
        comment.put("imgSrc","data:image/jpeg;base64,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");
        comment.put("text","简单和微笑");
        comment.put("comment","酒店位置非常方便，很多景点和商场都在附近。酒店的房间舒适宽敞，员工服务专业周到，早餐也很好吃。");
        comments.add(comment);

        comment=new HashMap<>();
        comment.put("imgSrc","https://tse3-mm.cn.bing.net/th/id/OIP-C.lkqGPGQMYGQSmtvlu1rTnwAAAA?w=209&h=210&c=7&r=0&o=5&dpr=1.3&pid=1.7");
        comment.put("text","大漠祥云");
        comment.put("comment","这家酒店的设施非常好，位置便捷，员工热情周到。房间宽敞舒适，床铺非常柔软舒适，也很安静。");
        comments.add(comment);

        for (Map<String,String> map : comments) {
            for (Map.Entry<String,String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ": " + value);
            }
            System.out.println("--------------------");
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rules", rules);
        responseData.put("hotel_name", hotel_name);
        responseData.put("hotel_massage", hotel_massage);
        responseData.put("image_url_list", image_url_list);
        responseData.put("character1", character1);
        responseData.put("character2", character2);
        responseData.put("percentage", percentage);
        responseData.put("tableData", tableData);
        responseData.put("facility", facility);
        responseData.put("comments", comments);

//        System.out.println(facility);
//        System.out.println("11");
//        System.out.println(comment);
//        System.out.println("22");
//        System.out.println(tableData);

        // 返回一个响应实体，包含处理结果和HTTP状态码
//        System.out.println(responseData);


        return responseData;
    }
}
