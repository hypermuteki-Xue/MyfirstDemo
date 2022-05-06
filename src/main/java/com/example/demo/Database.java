package com.example.demo;

import java.sql.*;

public class Database {
    public static Connection createTable() throws SQLException
    {
        //手动添加一个mydb1数据库
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","1234");
        Statement statement=connection.createStatement();
        DatabaseMetaData db=connection.getMetaData();
        ResultSet rs=db.getTables(null,null,"person",null);
        if(rs.next())
        {
            System.out.println("已经存在表了");
        }
        else
        {
            statement.execute("create table person(name varchar(40),number varchar (40))");
            System.out.println("建立数据表");
        }
        rs.close();
        statement.close();
        return connection;
    }
    public static boolean insertData(Connection connection,String name,String number) throws SQLException
    {
        PreparedStatement psql = connection.prepareStatement("insert into person(name,number)" + "values(?, ?)");
        psql.setString(1,name);
        psql.setString(2,number);
        psql.executeUpdate();
        psql.close();
        return true;
    }
    public static boolean deleteData(Connection connection,String t_name,String number) throws SQLException
    {
        PreparedStatement psql = connection.prepareStatement("delete from person where name=?;");
        psql.setString(1,t_name);
        psql.executeUpdate();
        psql.close();
       return true;
    }

}
