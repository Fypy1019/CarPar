package com.qf.Car_message.controller;

import com.qf.entity.Car_message;
import com.qf.Car_message.service.Car_messageService;
import com.qf.entity.User;
import com.qf.utils.Page;
import com.qf.utils.PageUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Car_message")
public class Car_messageController {
    @Resource
    private Car_messageService car_messageService;

    @GetMapping("/list")
    public String car_messageList(Model model,@RequestParam(defaultValue = "0") Integer index,String title){
        Integer count = car_messageService.carsCount(title);
        Page page = PageUtils.settingPage(index, count);
        List<Car_message> car_messages = car_messageService.car_messageList(index,title);
        model.addAttribute("car_messages",car_messages);
        model.addAttribute("page",page);
        model.addAttribute("title",title);
        return "car_message/car_messageList.html";
    }


    @PostMapping("/addCar_message")
    public String addCar_message(Car_message car_message, HttpSession session){
        User user = (User) session.getAttribute("login_user");
        car_message.setUserId(user.getUserId());
        car_message.setUserName(user.getUserName());
        Integer row = car_messageService.addCar_message(car_message);
        return "redirect:/Car_message/list";
    }


    @GetMapping("/findCar_messageById")
    public String findCar_messageById(Integer id,Integer flag,Model model){
        Car_message car_message = car_messageService.findCar_messageById(id);
        model.addAttribute("car_message",car_message);
        if (flag==1){
            return "car_message/car_messageView.html";
        }else {
            return "car_message/car_messageUpdate.html";
        }
    }


    @GetMapping("/deleteCar_messageById")
    public String deleteCar_messageById(Integer id){
        Integer row = car_messageService.deleteCar_messageById(id);
        return "redirect:/Car_message/list";
    }


    @PostMapping("/updateCar_message")
    public String updateCar_message(Car_message car_message){
        Integer row = car_messageService.updateCar_message(car_message);
        /*System.out.println(row);*/
        return "redirect:/Car_message/list";
    }
}

