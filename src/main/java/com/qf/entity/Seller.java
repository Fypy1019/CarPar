package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 15483 on 2021/4/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private Integer sellerId;
    private String sellerName;
    private String sellerPhone;
    private String sellerEmail;
    private String sellerAddress;

}
