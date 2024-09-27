package com.situ.emsvue.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.situ.emsvue.util.CommonUtil;
import com.situ.emsvue.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class CaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String captcha = defaultKaptcha.createText();
        log.info("图形验证码:{}", captcha);
        String key = CommonUtil.getCaptchaKey(request);
        redisTemplate.opsForValue().set(key,captcha,10*60*1000, TimeUnit.MILLISECONDS);
        BufferedImage image = defaultKaptcha.createImage(captcha);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        String base64Code = Base64.encodeBase64String(out.toByteArray());
        return Result.ok("", "data:image/png;base64," + base64Code);

    }
}
