package com.qf.seller.dao;

import com.qf.entity.News;
import com.qf.entity.Seller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 15483 on 2021/4/18.
 */
public class SellerProvider {

    public String sellerList(@Param("index") Integer index,@Param("title") String title){
        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("seller");
                if (title != null && !"".equals((title))) {
                    this.WHERE("seller_name LIKE '%${title}%'");
                }
                this.LIMIT("#{index},6");
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateSeller(@Param("seller") final Seller seller) {
        String sql = new SQL() {
            {
                this.UPDATE("seller");
                if (seller.getSellerName() != null && !"".equals(seller.getSellerName())) {
                    this.SET("seller_name = #{seller.sellerName}");
                }
                if (seller.getSellerPhone() != null&& !"".equals(seller.getSellerPhone())) {
                    this.SET("seller_phone = #{seller.sellerPhone}");
                }
                if (seller.getSellerEmail() != null&& !"".equals(seller.getSellerEmail())) {
                    this.SET("seller_email = #{seller.sellerEmail}");
                }
                if (seller.getSellerAddress() != null&& !"".equals(seller.getSellerAddress())) {
                    this.SET("seller_address = #{seller.sellerAddress}");
                }
                this.WHERE("seller_id=#{seller.sellerId}");
            }
        }.toString();
        return sql;
    }
}
