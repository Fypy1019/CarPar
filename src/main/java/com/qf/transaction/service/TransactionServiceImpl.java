package com.qf.transaction.service;

import com.qf.entity.*;
import com.qf.transaction.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 15483 on 2021/4/13.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Override
    public List<Car_message> transactionList(Integer index,Car_message car_message, Integer maxPrice, Integer minPrice) {
        return transactionDao.transactionList(index,car_message,maxPrice,minPrice);
    }

    @Override
    public Car_message findTransactionCar_messageById(Integer id) {
        return transactionDao.findTransactionCar_messageById(id);
    }

    @Override
    public Integer addTransactionCar_message(Car_message car_message) {
        return transactionDao.addTransactionCar_message(car_message);
    }

    @Override
    public List<Car_message> firstTransactionList() {
        return transactionDao.firstTransactionList();
    }

    @Override
    public User findUserById(Integer id) {
        return transactionDao.findUserById(id);
    }

    @Override
    public Integer updateUserMessage(User user) {
        return transactionDao.updateUserMessage(user);
    }

    @Override
    public List<News> newsList() {
        return transactionDao.newsList();
    }

    @Override
    public List<News> firstNewsList() {
        return transactionDao.firstNewsList();
    }

    @Override
    public News findTransactionNewsById(Integer id) {
        return transactionDao.findTransactionNewsById(id);
    }

    @Override
    public List<Seller> sellerList() {
        return transactionDao.sellerList();
    }

    @Override
    public List<Car_message> myCar(User user) {
        return transactionDao.myCar(user);
    }

    @Override
    public List<Car_message> myOrders(User user) {
        return transactionDao.myOrders(user);
    }

    @Override
    public Integer updateMyCar(Car_message car_message) {
        return transactionDao.updateMyCar(car_message);
    }

    @Override
    public Integer deleteMyCarById(Integer id) {
        return transactionDao.deleteMyCarById(id);
    }

//    @Override
//    public List<Car_message> findCar_message(String carname, String carbodytype, String cartransmission, Integer caryear, String carfueltype) {
//        return transactionDao.findCar_message(carname,carbodytype,cartransmission,caryear,carfueltype);
//    }


    @Override
    public Integer transactionListCount(Car_message car_message, Integer maxPrice, Integer minPrice) {
        return transactionDao.transactionListCount(car_message,maxPrice,minPrice);
    }

    @Override
    public void addOrder(Orders orders) {
        transactionDao.addOrder(orders);
    }

    @Override
    public Map<String, Object> findOrderById(String orderId) {
        //修改支付状态
        transactionDao.updateOrderStatus(orderId);
        Map<String, Object> orderById = transactionDao.findOrderById(orderId);
        //修改汽车状态
        transactionDao.updateCarStatus((Integer) orderById.get("carId"));
        return orderById;
    }

}