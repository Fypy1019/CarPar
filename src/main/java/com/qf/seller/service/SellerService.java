package com.qf.seller.service;

import com.qf.entity.Seller;

import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
public interface SellerService {
    List<Seller> sellerList(Integer index,String title);

    Seller findSellerById(Integer id);

    Integer deleteSellerById(Integer id);

    Integer updateSeller(Seller seller);

    Integer addSeller(Seller seller);

    Integer sellerCount(String title);
}
