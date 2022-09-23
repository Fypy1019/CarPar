package com.qf.Car_message.dao;

import com.qf.entity.Car_message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class Car_messageProvider {

    public String car_messageList(@Param("index") Integer index,@Param("title") String title){
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("car_message");
                if (title != null && !"".equals((title))) {
                    this.WHERE("car_name LIKE '%${title}%'");
                }
                this.LIMIT("#{index},6");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateCar_message(@Param("Car_message") Car_message car_message) {

        String sql = new SQL() {
            {
                this.UPDATE("Car_message");
                if (car_message.getCarName() != null && !"".equals(car_message.getCarName())) {
                    this.SET("car_name=#{Car_message.carName}");
                }
                if (car_message.getCarPrice() != null && !"".equals(car_message.getCarPrice())) {
                    this.SET("car_price=#{Car_message.carPrice}");
                }
                if (car_message.getCarBodytype() != null && !"".equals(car_message.getCarBodytype())) {
                    this.SET("car_bodytype=#{Car_message.carBodytype}");
                }
                if (car_message.getCarEnginesize() != null && !"".equals(car_message.getCarEnginesize())) {
                    this.SET("car_enginesize=#{Car_message.carEnginesize}");
                }
                if (car_message.getCarTransmission() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_transmission=#{Car_message.carTransmission}");
                }
                if (car_message.getCarServicehistory() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_servicehistory=#{Car_message.carServicehistory}");
                }
                if (car_message.getCarMileage() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_mileage=#{Car_message.carMileage}");
                }
                if (car_message.getCarYear() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_year=#{Car_message.carYear}");
                }
                if (car_message.getUserName() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("user_name=#{Car_message.userName}");
                }
                if (car_message.getCarFueltype() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_fueltype=#{Car_message.carFueltype}");
                }
                if (car_message.getCarExteriorcolor() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_exteriorcolor=#{Car_message.carExteriorcolor}");
                }
                if (car_message.getCarInteriorcolor() != null && !"".equals(car_message.getCarTransmission())) {
                    this.SET("car_interiorcolor=#{Car_message.carInteriorcolor}");
                }

                this.WHERE("car_id=#{Car_message.carId}");
            }
        }.toString();
        return sql;
    }
}
