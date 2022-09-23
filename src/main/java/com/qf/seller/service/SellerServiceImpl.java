package com.qf.seller.service;

import com.qf.entity.Seller;
import com.qf.seller.dao.SellerDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerDao sellerDao;
    @Override
    public List<Seller> sellerList(Integer index,String title) {
        return sellerDao.sellerList(index,title);
    }

    @Override
    public Seller findSellerById(Integer id) {
        return sellerDao.findSellerById(id);
    }

    @Override
    public Integer deleteSellerById(Integer id) {
        return sellerDao.deleteSellerById(id);
    }

    @Override
    public Integer updateSeller(Seller seller) {
        return sellerDao.updateSeller(seller);
    }

    @Override
    public Integer addSeller(Seller seller) {
        return sellerDao.addSeller(seller);
    }

    @Override
    public Integer sellerCount(String title) {
        return sellerDao.sellerCount(title);
    }
}
