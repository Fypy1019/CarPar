package com.qf.user.dao;

import com.qf.entity.Car_message;
import com.qf.entity.News;
import com.qf.entity.User;
import com.qf.news.dao.NewsProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE (user_name=#{name} OR user_email=#{name}) AND user_pw=#{password}")
    User login(@Param("name") String name,@Param("password") String password);

    @SelectProvider(type = UserProvider.class, method = "userList")
    List<User> userList(@Param("index") Integer index,@Param("title") String title);

    @Delete("DELETE FROM user WHERE user_id=#{id}")
    Integer deleteUserById(Integer id);

    @Select("SELECT * FROM user WHERE user_id=#{id}")
    User findUserById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(@Param("user") User user);

    @Insert("INSERT INTO user (user_pw,user_name,user_email,user_sex,user_phone,user_address,user_type,user_age)\n" +
            "VALUES(#{user.userPw},#{user.userName},#{user.userEmail},#{user.userSex},#{user.userPhone},#{user.userAddress},#{user.userType},#{user.userAge})")
    Integer addUser(@Param("user") User user);

    @Select("select * from car_message WHERE is_delete=0 ORDER BY create_time DESC limit 6")
    List<Car_message> firstTransactionList();

    @Select("select * from news limit 6 ")
    List<News> firstNewsList();

    @Select("SELECT COUNT(*) FROM user WHERE user_name LIKE '%${title}%'")
    Integer userCount(@Param("title") String title);
}
