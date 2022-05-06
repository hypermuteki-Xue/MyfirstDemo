package com.example.demo;

import com.sun.deploy.security.SelectableSecurityManager;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import  java.sql.*;

@WebServlet(name = "helloServlet", value = "/rs")
public class myser extends HttpServlet {
  static{
      try{
          Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e){
          e.printStackTrace();
      }
  }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name= req.getParameter("name");
        String number=req.getParameter("number");
        try {
            Connection connection=Database.createTable();
           if(number.startsWith("-"))
           {
               if(Database.deleteData(connection,name,number))
               {
                   req.getRequestDispatcher("/success.html").forward(req,resp);
                   System.out.println("成功");
               }
               else
               {
                   req.getRequestDispatcher("/failure.html").forward(req,resp);
                   System.out.println("失败");
               }
           }
           else
           {
               if(Database.insertData(connection,name,number))
               {
                   req.getRequestDispatcher("/success.html").forward(req,resp);
                   System.out.println("成功");
               }
               else
               {
                   req.getRequestDispatcher("/failure.html").forward(req,resp);
                   System.out.println("失败");
               }
           }
           connection.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}