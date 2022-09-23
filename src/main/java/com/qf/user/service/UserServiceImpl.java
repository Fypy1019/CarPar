package com.qf.user.service;

import com.qf.entity.Car_message;
import com.qf.entity.News;
import com.qf.entity.User;
import com.qf.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String name, String password) {
        return userDao.login(name,password);
    }

    @Override
    public List<User> userList(Integer index,String title) {
        return userDao.userList(index,title);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<Car_message> firstTransactionList() {return userDao.firstTransactionList();}

    @Override
    public List<News> firstNewsList() {
        return userDao.firstNewsList();
    }

    @Override
    public Integer userCount(String title) {
        return userDao.userCount(title);
    }

}
