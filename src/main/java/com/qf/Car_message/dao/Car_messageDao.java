package com.qf.Car_message.dao;

import com.qf.entity.Car_message;
import com.qf.news.dao.NewsProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Car_messageDao {
    @SelectProvider(type = Car_messageProvider.class, method = "car_messageList")
    List<Car_message> car_messageList(@Param("index") Integer index,@Param("title") String title);

   /* @Insert("INSERT INTO car_message (car_id,car_name,car_price,car_bodytype,car_enginesize,car_transmission,car_servicehistory,car_mileage,car_year,car_owners,car_fueltype,car_exteriorcolor,car_interiorcolor)" +
            "VALUES (#{Car_message.carName},#{Car_message.carPrice},#{Car_message.carBodytype},#{Car_message.carEnginesize},#{Car_message.carTransmission},#{Car_message.carServicehistory},#{Car_message.carMileage},#{Car_message.carYear},#{Car_message.carOwners},#{Car_message.carFueltype},#{Car_message.carExteriorcolor},#{Car_message.carInteriorcolor})")
    Integer addCar_message(@Param("Car_message") Car_message car_message);*/
   @Insert("INSERT INTO car_message (car_name,car_price,car_bodytype,car_enginesize,car_transmission,car_servicehistory,car_mileage,car_year,user_name,car_fueltype,car_exteriorcolor,car_interiorcolor,user_id,car_url)" +
           "VALUES (#{Car_message.carName},#{Car_message.carPrice},#{Car_message.carBodytype},#{Car_message.carEnginesize},#{Car_message.carTransmission},#{Car_message.carServicehistory},#{Car_message.carMileage},#{Car_message.carYear},#{Car_message.userName},#{Car_message.carFueltype},#{Car_message.carExteriorcolor},#{Car_message.carInteriorcolor},#{Car_message.userId},'default.jpg')")
   Integer addCar_message(@Param("Car_message") Car_message car_message);

    @Select("SELECT * FROM Car_message WHERE car_id=#{id}")
    Car_message findCar_messageById(@Param("id") Integer id);

    @Delete("DELETE FROM Car_message WHERE car_id=#{id}")
    Integer deleteCar_messageById(@Param("id") Integer id);

    @UpdateProvider(type = Car_messageProvider.class, method = "updateCar_message")
    Integer updateCar_message(@Param("Car_message") Car_message car_message);

    @Select("SELECT COUNT(*) FROM car_message WHERE car_name LIKE '%${title}%'")
    Integer carsCount(@Param("title") String title);
}
