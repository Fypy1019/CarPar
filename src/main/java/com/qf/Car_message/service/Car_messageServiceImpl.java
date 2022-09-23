package com.qf.Car_message.service;

import com.qf.Car_message.dao.Car_messageDao;
import com.qf.entity.Car_message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Car_messageServiceImpl implements Car_messageService{
    @Resource
    private Car_messageDao car_messageDao;
    @Override
    public List<Car_message> car_messageList(Integer index,String title) {
        return car_messageDao.car_messageList(index,title);
    }

    @Override
    public Integer addCar_message(Car_message car_message) {

        return car_messageDao.addCar_message(car_message);
    }

    @Override
    public Car_message findCar_messageById(Integer id) {

        return car_messageDao.findCar_messageById(id);
    }


    @Override
    public Integer deleteCar_messageById(Integer id) {

        return car_messageDao.deleteCar_messageById(id);
    }

   /* @Override
    public Integer deleteMessageById(Integer id) {
        billDao.deleteBillByCar_message(id);
        return car_messageDao.deleteCar_messageById(id);
    }*/


    @Override
    public Integer updateCar_message(Car_message car_message) {

        return car_messageDao.updateCar_message(car_message);
    }

    @Override
    public Integer carsCount(String title) {

        return car_messageDao.carsCount(title);
    }
}
