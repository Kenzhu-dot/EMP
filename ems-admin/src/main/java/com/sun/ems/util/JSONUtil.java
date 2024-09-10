package com.sun.ems.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSONUtil {

    public JSONUtil() {
    }

    public static void toJSON(HttpServletResponse resp,Object object){
        resp.setContentType("text/html;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(resp.getWriter(),object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
