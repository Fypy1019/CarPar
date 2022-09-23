package com.qf.utils;
import java.util.Calendar;
import java.util.Date;

public class AgeByBirthday {
    public Integer getAgeByBirth(Date birthday){
        Calendar cal = Calendar.getInstance();
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        //取出当前年月日
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        //取出出生年月日
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        //大概年龄是当前年减
        // 去出生年
        int age = yearNow - yearBirth;
        //如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁
        if(monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)){
            age--;
        }
        return age;
    }
}