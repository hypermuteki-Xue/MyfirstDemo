package database;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userMapper {
   List<User> selectAll();
   User selectByName(@Param("username") String username);
   void addUser(@Param("username") String username,@Param("password") String password);
   void delUser(@Param("username") String username);
}
