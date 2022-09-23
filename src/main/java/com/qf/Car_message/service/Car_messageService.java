package com.qf.Car_message.service;

import com.qf.entity.Car_message;

import java.util.List;

public interface Car_messageService {
    List<Car_message> car_messageList(Integer index,String title);

    Integer addCar_message(Car_message car_message);

     Car_message findCar_messageById(Integer id);

    Integer deleteCar_messageById(Integer id);


    Integer updateCar_message(Car_message car_message);

        Integer carsCount(String title);
        }
