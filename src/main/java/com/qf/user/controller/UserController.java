package com.qf.user.controller;

import com.qf.entity.Car_message;
import com.qf.entity.News;
import com.qf.entity.Result;
import com.qf.entity.User;
import com.qf.transaction.controller.TransactionController;
import com.qf.user.service.UserService;
import com.qf.utils.Page;
import com.qf.utils.PageUtils;
import com.sun.deploy.security.ValidationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String name, String password, HttpSession session, Model model){
        User login_user = userService.login(name,password);
        if (login_user!=null){
            session.setAttribute("login_user",login_user);
            if(login_user.getUserType() == 0){
                return "index.html";
            }
              else{
                List<Car_message> firstTransactions = userService.firstTransactionList();
                model.addAttribute("firstTransactions", firstTransactions);

                List<News> firstNewss = userService.firstNewsList();
                model.addAttribute("firstNewss", firstNewss);
                return "firstpage.html";
            }
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "login.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("login_user");
        model.addAttribute("msg","退出成功");
        return "login.html";
    }

    @GetMapping("/list")
    public String userList(Model model,@RequestParam(defaultValue = "0") Integer index,String title){
        Integer count = userService.userCount(title);
        Page page = PageUtils.settingPage(index, count);
        List<User> users = userService.userList(index,title);
        for (User user : users) {
            user.setAge(user.getUserAge());
        }
        model.addAttribute("page",page);
        model.addAttribute("users",users);
        model.addAttribute("title",title);
        return "user/userList.html";
    }

    @GetMapping("/findUserById")
    public String findUserById(Integer id,Integer flag,Model model){
        User user = userService.findUserById(id);
        user.setAge(user.getUserAge());
        model.addAttribute("user",user);
        if (flag==1){
            return "user/userView.html";
        }else {

            return "user/userUpdate.html";
        }
    }

    @GetMapping("/deleteUserById")
    public String deleteUserById(Integer id,Model model){
        Integer row = userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user){
        Integer row = userService.updateUser(user);
        return "redirect:/user/findUserById?id="+user.getUserId()+"&flag="+2;
    }

    @PostMapping("/addUser")
    public String addUser(User user,Model model){
        Integer row = userService.addUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/register")
    public String register(User user,Model model){
        user.setAge(new Date());
        user.setUserAge(new Date());
        user.setUserSex(0);
        user.setUserType(2);
        Integer row = userService.addUser(user);
        if (row==1){
            model.addAttribute("msg","注册成功，点击登录");
        }
        return "login.html";
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public Result updatePwd(String pwd1,String pwd2,String pwd3,HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        if (!pwd1.equals(login_user.getUserPw())){
            return new Result(1,"原始密码不正确");
        }else {
            if (!pwd2.equals(pwd3)){
                return new Result(2,"两次密码不一致");
            }else {
                login_user.setUserPw(pwd2);
                Integer row = userService.updateUser(login_user);
                return new Result(0,"修改成功，请重新登录");
            }
        }
    }
}
