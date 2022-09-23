package com.qf.seller.dao;

import com.qf.entity.News;
import com.qf.entity.Seller;
import com.qf.news.dao.NewsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Mapper
public interface SellerDao {

    @SelectProvider(type = SellerProvider.class, method = "sellerList")
    List<Seller> sellerList(@Param("index") Integer index,@Param("title") String title);

    @Select("SELECT * FROM seller WHERE seller_id=#{id}")
    Seller findSellerById(@Param("id") Integer id);

    @Delete("DELETE FROM seller WHERE seller_id=#{id}")
    Integer deleteSellerById(@Param("id") Integer id);

    @UpdateProvider(type = SellerProvider.class, method = "updateSeller")
    Integer updateSeller(@Param("seller") Seller seller);

    @Insert("INSERT INTO seller (seller_name,seller_phone,seller_email,seller_address)" +
            "VALUES(#{seller.sellerName},#{seller.sellerPhone},#{seller.sellerEmail},#{seller.sellerAddress})")
    Integer addSeller(@Param("seller") Seller seller);

    @Select("SELECT COUNT(*) FROM seller WHERE seller_name LIKE '%${title}%'")
    Integer sellerCount(@Param("title") String title);
}
