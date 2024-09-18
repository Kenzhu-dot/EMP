package com.situ.ems_spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/**")
    public String service(HttpServletRequest req){
        String requestURI = req.getRequestURI();
        //System.out.println("requestURI: " + requestURI);
        String[] paths = requestURI.split("/");
        /*System.out.println("pathslength:"+paths.length);
        System.out.println(Arrays.toString(paths));*/
        System.out.println(Arrays.toString(paths));
        switch (paths.length) {
            case 3:
                return paths[2];
            case 4:
                return paths[2]+"_"+paths[3];
        }
        return "index";
    }
}
