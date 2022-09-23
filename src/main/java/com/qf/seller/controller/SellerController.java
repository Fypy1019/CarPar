package com.qf.seller.controller;

import com.qf.entity.News;
import com.qf.entity.Seller;
import com.qf.seller.service.SellerService;
import com.qf.utils.Page;
import com.qf.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 15483 on 2021/4/18.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;

    @GetMapping("/list")
    public String sellerList(Model model,@RequestParam(defaultValue = "0") Integer index,String title){
        Integer count = sellerService.sellerCount(title);
        Page page = PageUtils.settingPage(index, count);
        List<Seller> sellerss = sellerService.sellerList(index,title);
        model.addAttribute("sellers",sellerss);
        model.addAttribute("page",page);
        model.addAttribute("title",title);
        return "seller/sellerList.html";
    }
    @GetMapping("/findSellerById")
    public String findSellerById(Integer id,Integer flag,Model model){
        Seller seller = sellerService.findSellerById(id);
        model.addAttribute("seller",seller);
        if (flag==1){
            return "seller/sellerView.html";
        }else {
            return "seller/sellerUpdate.html";
        }
    }

    @GetMapping("/deleteSellerById")
    public String deleteSellerById(Integer id,Model model){
        Integer row = sellerService.deleteSellerById(id);
        return "redirect:/seller/list";
    }

    @PostMapping("/updateSeller")
    public String updateSeller(Seller seller){
        Integer row = sellerService.updateSeller(seller);
        return "redirect:/seller/findSellerById?id="+seller.getSellerId()+"&flag="+2;
    }

    @PostMapping("/addSeller")
    public String addSeller(Seller seller){
        Integer row = sellerService.addSeller(seller);
        return "redirect:/seller/list";
    }
}
