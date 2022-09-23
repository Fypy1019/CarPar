package com.qf.transaction.dao;

import com.qf.entity.Car_message;
import com.qf.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 15483 on 2021/4/13.
 */
public class TransactionProvider {
    /*  public String findBill(@Param("name") String name, @Param("status") Integer status,@Param("Car_message") Integer car_message) {
      String sql = new SQL() {
          {
              this.SELECT("*");
              this.FROM("bill");
              if (name!= null && !"".equals(name)) {
                  this.WHERE("bill_name LIKE '%${name}%'");
              }
              if (status != null) {
                  this.WHERE("bill_status=#{status}");
              }
              if (car_message != null) {
                  this.WHERE("car_id=#{Car_message}");
              }
          }
      }.toString();
      return sql;
  }*/


    public String transactionList(@Param("index") Integer index,@Param("car_message") Car_message car_message,@Param("maxPrice") Integer maxPrice,@Param("minPrice") Integer minPrice) {
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("car_message");
                if (maxPrice != null && !"".equals((maxPrice))) {
                    this.WHERE("car_price <= #{maxPrice}");
                }
                if (maxPrice != null && !"".equals((minPrice))) {
                    this.WHERE("car_price >= #{minPrice}");
                }
                if (car_message.getCarName() != null && !"".equals((car_message.getCarName()))) {
                    this.WHERE("car_name LIKE '%${car_message.carName}%'");
                }
                if (car_message.getCarBodytype() != null && !"".equals((car_message.getCarBodytype()))) {
                    this.WHERE("car_bodytype LIKE '%${car_message.carBodytype}%'");
                }
                if (car_message.getCarTransmission() != null && !"".equals((car_message.getCarTransmission()))) {
                    this.WHERE("car_transmission LIKE '%${car_message.carTransmission}%'");
                }
                if (car_message.getCarYear() != null && !"".equals((car_message.getCarYear()))) {
                    this.WHERE("car_year=#{car_message.carYear}");
                }
                if (car_message.getCarFueltype() != null && !"".equals((car_message.getCarFueltype()))) {
                    this.WHERE("car_fueltype LIKE '%${car_message.carFueltype}%'");
                }
                this.WHERE("is_delete=0");
                this.ORDER_BY("create_time DESC");
                this.LIMIT("#{index},6");
            }
        }.toString();
        return sql;
    }

    public String transactionListCount(@Param("car_message") Car_message car_message,@Param("maxPrice") Integer maxPrice,@Param("minPrice") Integer minPrice) {
        String sql = new SQL() {
            {
                this.SELECT("COUNT(*)");
                this.FROM("car_message");
                if (maxPrice != null && !"".equals((maxPrice))) {
                    this.WHERE("car_price <= #{maxPrice}");
                }
                if (maxPrice != null && !"".equals((minPrice))) {
                    this.WHERE("car_price >= #{minPrice}");
                }
                if (car_message.getCarName() != null && !"".equals((car_message.getCarName()))) {
                    this.WHERE("car_name LIKE '%${car_message.carName}%'");
                }
                if (car_message.getCarBodytype() != null && !"".equals((car_message.getCarBodytype()))) {
                    this.WHERE("car_bodytype LIKE '%${car_message.carBodytype}%'");
                }
                if (car_message.getCarTransmission() != null && !"".equals((car_message.getCarTransmission()))) {
                    this.WHERE("car_transmission LIKE '%${car_message.carTransmission}%'");
                }
                if (car_message.getCarYear() != null && !"".equals((car_message.getCarYear()))) {
                    this.WHERE("car_year=#{car_message.carYear}");
                }
                if (car_message.getCarFueltype() != null && !"".equals((car_message.getCarFueltype()))) {
                    this.WHERE("car_fueltype LIKE '%${car_message.carFueltype}%'");
                }
                this.WHERE("is_delete=0");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }
    public String updateUserMessage(@Param("user") final User user) {
        String sql = new SQL() {
            {
                this.UPDATE("user");
                if (user.getUserName() != null && !"".equals(user.getUserName())) {
                    this.SET("user_name=#{user.userName}");
                }
                if (user.getUserEmail() != null && !"".equals(user.getUserEmail())) {
                    this.SET("user_email=#{user.userEmail}");
                }
                if (user.getUserSex() != null) {
                    this.SET("user_sex=#{user.userSex}");
                }
                if (user.getUserPhone() != null && !"".equals(user.getUserPhone())) {
                    this.SET("user_phone=#{user.userPhone}");
                }
                if (user.getUserAddress() != null && !"".equals(user.getUserAddress())) {
                    this.SET("user_address=#{user.userAddress}");
                }
                if (user.getUserType() != null && !"".equals(user.getUserType())) {
                    this.SET("user_type=#{user.userType}");
                }
                if (user.getUserPw() != null && !"".equals(user.getUserPw())) {
                    this.SET("user_pw=#{user.userPw}");
                }
                if (user.getUserAge() != null && !"".equals(user.getUserAge())) {
                    this.SET("user_age=#{user.userAge}");
                }
                this.WHERE("user_id=#{user.userId}");
            }
        }.toString();
        return sql;
    }
    public String updateMyCar(@Param("car_message") final Car_message car_message) {
        String sql = new SQL() {
            {
                this.UPDATE("car_message");
                if (car_message.getCarPrice() != null && !"".equals(car_message.getCarPrice())) {
                    this.SET("car_price=#{car_message.carPrice}");
                }
                if (car_message.getCarServicehistory() != null && !"".equals(car_message.getCarServicehistory())) {
                    this.SET("car_servicehistory=#{car_message.carServicehistory}");
                }
                if (car_message.getCarMileage() != null && !"".equals(car_message.getCarMileage())) {
                    this.SET("car_mileage=#{car_message.carMileage}");
                }
                this.WHERE("car_id=#{car_message.carId}");
            }
        }.toString();
        return sql;
    }


}
//    public String findCar_message(@Param("car_message") Car_message car_message){
//        String sql = new SQL(){
//            {
//                this.SELECT("car_message");
//                if (car_message.getCarName() != null && !"".equals(car_message.getCarName())) {
//                    this.ADD_ROW("car_name LIKE #{car_message.carName}");
//                }
//                if (car_message.getCarBodytype() != null && !"".equals(car_message.getCarBodytype())) {
//                    this.SET("car_bodytype LIKE #{car_message.carBodytype}");
//                }
//                if (car_message.getCarTransmission() != null && !"".equals(car_message.getCarTransmission())) {
//                    this.SET("car_transmission LIKE #{car_message.carTransmission}");
//                }
//                if (car_message.getCarYear() != null && !"".equals(car_message.getCarYear())) {
//                    this.SET("car_year=#{car_message.carYear}");
//                }
//                if (car_message.getCarFueltype() != null && !"".equals(car_message.getCarFueltype())) {
//                    this.SET("car_fueltype LIKE #{car_message.carFueltype}");
//                }
//            }
//
//            private void ADD_ROW(String s) {
//            }
//        }.toString();
//        return sql;
//    }

