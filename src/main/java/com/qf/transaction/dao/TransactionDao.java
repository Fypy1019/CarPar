package com.qf.transaction.dao;

import com.qf.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 15483 on 2021/4/13.
 */
@Mapper
public interface TransactionDao {

    @SelectProvider(type = TransactionProvider.class, method = "transactionList")
    List<Car_message> transactionList(@Param("index") Integer index,@Param("car_message") Car_message car_message,@Param("maxPrice") Integer maxPrice,@Param("minPrice") Integer minPrice);

    @Select("SELECT * FROM Car_message WHERE car_id=#{id}")
    Car_message findTransactionCar_messageById(@Param("id") Integer id);

    @Insert("INSERT INTO car_message (car_name,car_price,car_bodytype,car_enginesize,car_transmission,car_servicehistory,car_mileage,car_year,user_name,car_fueltype,car_exteriorcolor,car_interiorcolor,user_id,car_url,create_time)" +
            "VALUES (#{Car_message.carName},#{Car_message.carPrice},#{Car_message.carBodytype},#{Car_message.carEnginesize},#{Car_message.carTransmission},#{Car_message.carServicehistory},#{Car_message.carMileage},#{Car_message.carYear},#{Car_message.userName},#{Car_message.carFueltype},#{Car_message.carExteriorcolor},#{Car_message.carInteriorcolor},#{Car_message.userId},#{Car_message.carUrl},NOW())")
    Integer addTransactionCar_message(@Param("Car_message") Car_message car_message);

    @Select("select * from car_message WHERE is_delete=0 ORDER BY create_time DESC limit 6")
    List<Car_message> firstTransactionList();
//
//    @Select("SELECT * FROM Car_message WHERE car_name LIKE #{car_message}")
//    List<Car_message> findCar_messageByName(@Param("car_message") String car_message);

//    @Select("SELECT * FROM Car_message WHERE car_name LIKE #{car_message.carName}")
//    List<Car_message> findTransactionCar_messageByName(@Param("car_message") Car_message car_message);

    @Select("SELECT * FROM user WHERE user_id=#{id}")
    User findUserById(@Param("id") Integer id);

    @UpdateProvider(type = TransactionProvider.class, method = "updateUserMessage")
    Integer updateUserMessage(@Param("user") User user);

    @Select("select * from news ")
    List<News> newsList();

    @Select("select * from news limit 6 ")
    List<News> firstNewsList();

    @Select("SELECT * FROM news WHERE news_id=#{id}")
    News findTransactionNewsById(@Param("id") Integer id);

    @Select("select * from seller limit 3 ")
    List<Seller> sellerList();

    @Select("select * from car_message WHERE user_id=#{user.userId}")
    List<Car_message> myCar(@Param("user") User user);

    @Select("SELECT c.* FROM orders o LEFT JOIN car_message c ON o.car_id=c.car_id WHERE o.user_id=#{user.userId} AND o.status=1")
    List<Car_message> myOrders(@Param("user") User user);

    @UpdateProvider(type = TransactionProvider.class, method = "updateMyCar")
    Integer updateMyCar(@Param("car_message") Car_message car_message);

    @Delete("DELETE FROM Car_message WHERE car_id=#{id}")
    Integer deleteMyCarById(Integer id);

    @SelectProvider(type = TransactionProvider.class, method = "transactionListCount")
    Integer transactionListCount(@Param("car_message") Car_message car_message,@Param("maxPrice") Integer maxPrice,@Param("minPrice") Integer minPrice);

    @Insert("INSERT INTO orders (order_id,user_id,car_id,status,amount,create_time)" +
            "VALUES(#{order.orderId},#{order.userId},#{order.carId},#{order.status},#{order.amount},NOW())")
    void addOrder(@Param("order") Orders orders);

    @Update("UPDATE orders SET status=1 WHERE order_id=#{orderId}")
    void updateOrderStatus(@Param("orderId") String orderId);

    @Select("SELECT o.order_id AS orderId,DATE_FORMAT(o.create_time,'%Y-%m-%d %H:%i:%s') AS createTime,o.amount AS amount," +
            "c.car_id AS carId,c.car_url AS carUrl,c.car_name AS carName," +
            "u.user_name AS userName,u.user_email AS userEmail,u.user_phone AS userPhone " +
            "FROM orders o LEFT JOIN car_message c ON o.car_id=c.car_id " +
            "LEFT JOIN user u ON c.user_id=u.user_id " +
            "WHERE o.order_id=#{orderId}")
    Map<String,Object> findOrderById(@Param("orderId") String orderId);

    @Update("UPDATE Car_message SET is_delete=1 WHERE car_id=#{carId}")
    void updateCarStatus(@Param("carId") Integer carId);




//    @Select("SELECT * FROM Car_message WHERE car_name LIKE #{car_message.carName} AND car_bodytype LIKE #{car_message.carBodytype} AND car_transmission LIKE #{car_message.carTransmission} AND car_year= #{car_message.carYear} AND car_fueltype LIKE #{car_message.carFueltype} is not null")
//    List<Car_message> findCar_message(@Param("car_message")Car_message car_message);


}
