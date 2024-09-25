package com.situ.ems_spring.controller;


import com.situ.ems_spring.constants.RedisConstants;
import com.situ.ems_spring.util.AliOSSUtils;
import com.situ.ems_spring.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class UpLoadController {
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/upload")
    public Result uploadImage(MultipartFile file){
        System.out.println("-----------------------------------------------------------------------");
        //f7a9f3e6805a4e81b5d27245c6c30070
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // a.png
        String filename = file.getOriginalFilename();
        // .png
        String extension = filename.substring(filename.lastIndexOf("."));
        // f7a9f3e6805a4e81b5d27245c6c30070.png
        String newFileName = uuid + extension;

        String url = null;
        try {
           url =  AliOSSUtils.uploadFile(newFileName,file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*String filePath = "D:\\Demo\\ProgrameDemo\\blogimage\\" + newFilename;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        redisTemplate.opsForSet().add(RedisConstants.UPLOAD_IMAGE, url);
        return Result.ok("上传成功", url);
    }
}
