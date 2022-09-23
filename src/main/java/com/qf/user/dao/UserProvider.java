package com.qf.user.dao;

import com.qf.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String userList(@Param("index") Integer index,@Param("title") String title){
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("user");
                if (title != null && !"".equals((title))) {
                    this.WHERE("user_name LIKE '%${title}%'");
                }
                this.LIMIT("#{index},6");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateUser(@Param("user") final User user) {
        String sql = new SQL() {
            {
                this.UPDATE("user");
                if (user.getUserName() != null && !"".equals(user.getUserName())) {
                    this.SET("user_name=#{user.userName}");
                }
                if (user.getUserSex() != null) {
                    this.SET("user_sex=#{user.userSex}");
                }
                if (user.getUserAge() != null && !"".equals(user.getUserAge())) {
                    this.SET("user_age=#{user.userAge}");
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
                this.WHERE("user_id=#{user.userId}");
            }
        }.toString();
        return sql;
    }
}

