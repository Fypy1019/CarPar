package com.qf.transaction.service;

import com.qf.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 15483 on 2021/4/13.
 */
public interface TransactionService {
    List<Car_message> transactionList(Integer index,Car_message car_message, Integer maxPrice, Integer minPrice);

    Car_message findTransactionCar_messageById(Integer id);

    Integer addTransactionCar_message(Car_message car_message);

    List<Car_message> firstTransactionList();

    User findUserById(Integer id);

    Integer updateUserMessage(User user);

    List<News> newsList();

    List<News> firstNewsList();

    News findTransactionNewsById(Integer id);

    List<Seller> sellerList();

    List<Car_message> myCar(User user);

    List<Car_message> myOrders(User user);

    Integer updateMyCar(Car_message car_message);

    Integer deleteMyCarById(Integer id);

    Integer transactionListCount(Car_message car_message, Integer maxPrice, Integer minPrice);

    void addOrder(Orders orders);

    Map<String,Object> findOrderById(String orderId);




//    List<Car_message> findCar_message(String carname, String carbodytype, String cartransmission, Integer caryear, String carfueltype);
}
