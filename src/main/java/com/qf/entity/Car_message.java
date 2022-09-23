package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 15483 on 2021/3/22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car_message implements Serializable{
    private Integer carId;
    private String carName;
    private Integer carPrice;
    private String carBodytype;
    private String carEnginesize;
    private String carTransmission;
    private String carServicehistory;
    private Integer carMileage;
    private Integer carYear;
    private String userName;
    private String carFueltype;
    private String carExteriorcolor;
    private String carInteriorcolor;
    private Integer userId;
    private String carUrl;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
