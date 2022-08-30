package database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Database {
    private static SqlSession sqlSession=null;
    private static userMapper userMapper=null;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        userMapper userMapper =sqlSession.getMapper(database.userMapper.class);
    }
    public static boolean deleteData(String username)
    {
        userMapper.delUser(username);
        sqlSession.commit();
        return true;
    }
    public static boolean insertData(String username,String password)
    {
        userMapper.addUser(username,password);
        sqlSession.commit();
        return true;
    }
    public static User selectName(String username)
    {
       return userMapper.selectByName("username");
    }
    public static void closeAll()
    {
        sqlSession.close();
    }
}
