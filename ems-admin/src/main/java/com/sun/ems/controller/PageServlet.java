package com.sun.ems.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        //System.out.println("requestURI: " + requestURI);
        String[] paths = requestURI.split("/");
        /*System.out.println("pathslength:"+paths.length);
        System.out.println(Arrays.toString(paths));*/
        switch (paths.length) {
            case 2:
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                break;
            case 3:
                req.getRequestDispatcher("/WEB-INF/"+paths[2]+".jsp").forward(req, resp);
                break;
            case 4:
                req.getRequestDispatcher("/WEB-INF/"+paths[2]+"_"+paths[3]+".jsp").forward(req, resp);
        }
    }
}
