package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qf.utils.AgeByBirthday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userPw;
    private String userEmail;
    private Integer userSex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userAge;
    private String userPhone;
    private Integer userType;
    private String userAddress;
    private Integer age;


    public void setAge(Date birth) {
        AgeByBirthday ageByBirthday = new AgeByBirthday();
        this.age = ageByBirthday.getAgeByBirth(birth);
    }

}
