package web;

import database.Database;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import  java.sql.*;

@WebServlet(value = "/rs")
public class myser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username= req.getParameter("name");
        String password=req.getParameter("number");
        try {
           if(username.startsWith("-"))
           {
               if(Database.deleteData(username))
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("成功");
               }
               else
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("失败");
               }
           }
           else if(username.startsWith("+"))
           {
               if(Database.insertData(username,password))
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("成功");
               }
               else
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("失败");
               }
           }
           else {
               if(password!=null&&password.equals(Database.selectName(username).getPassWord()))
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("成功");
               }
               else
               {
                   //resp.sendRedirect("/success.html");
                   System.out.println("失败");
               }
           }
        } finally {
            Database.closeAll();
        }
    }
}