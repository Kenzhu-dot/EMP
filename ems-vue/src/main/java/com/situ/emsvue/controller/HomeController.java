package com.situ.emsvue.controller;

import com.situ.emsvue.pojo.VO.MessageVO;
import com.situ.emsvue.pojo.entity.Message;
import com.situ.emsvue.service.IHomeService;
import com.situ.emsvue.util.Result;
import com.situ.emsvue.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    IHomeService homeService;

    @GetMapping("/getMessageList/{MessageStatus}")
    public Result getMessageList(@PathVariable Integer MessageStatus){
        Map map = ThreadLocalUtil.get();
        Integer emId = (Integer) map.get("id");
        List<MessageVO> list = homeService.getMessageList(emId,MessageStatus);
        return Result.ok(list);
    }

    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestBody Message message){
        homeService.sendMessage(message);
        return  Result.ok("Waiting for the good news");
    }
}
