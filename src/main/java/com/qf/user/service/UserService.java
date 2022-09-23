package com.qf.user.service;

import com.qf.entity.Car_message;
import com.qf.entity.News;
import com.qf.entity.User;

import java.util.List;

public interface UserService {
    User login(String name, String password);

    List<User> userList(Integer index,String title);

    User findUserById(Integer id);

    Integer deleteUserById(Integer id);

    Integer updateUser(User user);

    Integer addUser(User user);

    List<Car_message> firstTransactionList();

    List<News> firstNewsList();

    Integer userCount(String title);

}
