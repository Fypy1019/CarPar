package com.qf.transaction.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.entity.*;
import com.qf.transaction.service.TransactionService;
import com.qf.utils.AlipayConfig;
import com.qf.utils.Page;
import com.qf.utils.PageUtils;
import com.qf.utils.RandomUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.removeAttribute("login_user");
        model.addAttribute("msg", "退出成功");
        return "login.html";
    }

    @Autowired
    private TransactionService transactionService;
    //首页-车辆、新闻
    @GetMapping("/transactionList")
    public String firstTransactionList(Model model) {
        List<Car_message> firstTransactions = transactionService.firstTransactionList();
        List<News> firstNewss = transactionService.firstNewsList();
        model.addAttribute("firstTransactions", firstTransactions);
        model.addAttribute("firstNewss", firstNewss);
        return "/firstPage.html";
    }
//价格查询
    @GetMapping("/TransactionList")
    public String transactionList(Model model, @RequestParam(defaultValue = "0") Integer index, Car_message car_message, Integer maxPrice, Integer minPrice) {
        Integer count = transactionService.transactionListCount(car_message, maxPrice, minPrice);
        Page page = PageUtils.settingPage(index, count);
        List<Car_message> car_messages = transactionService.transactionList(index, car_message, maxPrice, minPrice);
        model.addAttribute("car_messages", car_messages);
        model.addAttribute("car_message", car_message);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("page", page);
        return "/all-listings.html";
    }
//我上架的车辆
    @GetMapping("/myCar")
    public String myCar(HttpSession session, Model model, Integer flag) {
        User user = (User) session.getAttribute("login_user");
        List<Car_message> car_messages = null;
        car_messages = flag == 1 ? transactionService.myCar(user) : transactionService.myOrders(user);
        model.addAttribute("car_messages", car_messages);
        model.addAttribute("flag",flag);
        return "/myCar.html";
    }
//删除我上架的车辆
    @GetMapping("/deleteMyCarById")
    public String deleteMyCarById(Integer id) {
        Integer row = transactionService.deleteMyCarById(id);
        return "redirect:/transaction/myCar?flag="+1;
    }

    @GetMapping("/NewsList")
    public String newsList(Model model) {
        List<News> newss = transactionService.newsList();
        model.addAttribute("newss", newss);
        return "/all-news.html";
    }

    @GetMapping("/newsList")
    public String firstNewsList(Model model) {
        List<News> firstNewss = transactionService.firstNewsList();
        model.addAttribute("firstNewss", firstNewss);
        return "/firstPage.html";
    }

    @GetMapping("/sellerList")
    public String sellerList(Model model) {
        List<Seller> sellers = transactionService.sellerList();
        model.addAttribute("sellers", sellers);
        return "/sales-reps.html";
    }

    @GetMapping("/findTransactionNewsById")
    public String findTransactionNewsById(Integer id, Model model) {
        News news = transactionService.findTransactionNewsById(id);
        model.addAttribute("transactionNews", news);
        return "/news-single.html";
    }

    @PostMapping("/addTransactionCar_message")
    @ResponseBody   //使用此注解之后不再走视图处理器，而是直接将数据转成json输出
    public Result addTransactionCar_message(Car_message car_message, HttpSession session) {
        if (car_message.getCarUrl()==null || car_message.getCarUrl().equals("")){
            car_message.setCarUrl("default.jpg");
        }
        User user = (User) session.getAttribute("login_user");
        car_message.setUserId(user.getUserId());
        car_message.setUserName(user.getUserName());
        Integer row = transactionService.addTransactionCar_message(car_message);
        if (row>0){
            return new Result(1,"添加成功");
        }
        return new Result(0,"添加失败");
    }

    @GetMapping("/findTransactionCar_messageById")
    public String findTransactionCar_messageById(Integer id, Integer flag, Model model) {
        Car_message car_message = transactionService.findTransactionCar_messageById(id);
        model.addAttribute("transactionCar_message", car_message);
        if (flag == 1) {
            return "/message.html";
        } else {
            return "/updateMyCar.html";
        }
    }

//    @PostMapping("/findCar_message")
//    public String findCar_message(String carname,String carbodytype,String cartransmission,Integer caryear,String carfueltype,Model model){
//        List<Car_message> car_messages = transactionService.findCar_message(carname,carbodytype,cartransmission,caryear,carfueltype);
//        model.addAttribute("car_messages",car_messages);
//        return "/all-listings.html";
//    }

    @GetMapping("/findUserById")
    public String findUserById(Integer id, Integer flag, Model model,HttpSession session) {
        User user = transactionService.findUserById(id);
        user.setAge(user.getUserAge());
        model.addAttribute("user", user);
        if (flag == 1) {
            session.setAttribute("login_user",user);
            return "userMessage.html";
        } else {
            return "updateUserMessage.html";
        }
    }

    /*    @PostMapping("/updateUserMessage")
        public String updateUserMessage(User user){
            Integer row = transactionService.updateUserMessage(user);
            return "redirect:/transaction/findUserById?id="+user.getUserId()+"&flag="+2;
        }*/
    @PostMapping("/updateUserMessage")
    public String updateUserMessage(User user) {
        Integer row = transactionService.updateUserMessage(user);
        return "redirect:/transaction/findUserById?id=" + user.getUserId() + "&flag=" + 1;
    }

    @PostMapping("/updateMyCar")
    public String updateMyCar(Car_message car_message) {
        Integer row = transactionService.updateMyCar(car_message);
        return "redirect:/transaction/findTransactionCar_messageById?id=" + car_message.getCarId() + "&flag=" + 2;
    }

    @PostMapping("/updateUserPw")
    @ResponseBody  //使用此注解之后不再走视图处理器，而是直接将数据转成json字符串输出
    public Result updateUserPw(String pwd1, String pwd2, String pwd3, HttpSession session) {
        User login_user = (User) session.getAttribute("login_user");
        if (!pwd1.equals(login_user.getUserPw())) {
            return new Result(1, "原始密码不正确");
        } else {
            if (!pwd2.equals(pwd3)) {
                return new Result(2, "两次密码不一致");
            } else {
                login_user.setUserPw(pwd2);
                Integer row = transactionService.updateUserMessage(login_user);
                return new Result(0, "修改成功，请重新登录");
            }
        }
    }

    @Value("${filepath}")
    String filepath;

    //上传文件
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile upload, Model model) {
        String s = upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf("."));
        //上传文件
        String filename = RandomUrl.getUrl(8) + s;
        try {
            upload.transferTo(new File(filepath, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
       return new Result(0,filename);
    }

    //adclgm2096@sandbox.com
    //111111
    @GetMapping("/pay")
    public void pay(Integer carId, Double carPrice, HttpServletResponse response, HttpSession session) throws AlipayApiException, IOException {
        User login_user = (User) session.getAttribute("login_user");
        Orders orders = new Orders();
        orders.setOrderId(RandomUrl.getUrl(12));
        orders.setAmount(carPrice);
        orders.setUserId(login_user.getUserId());
        orders.setCarId(carId);
        orders.setStatus(0);
        transactionService.addOrder(orders);
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json",
                AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String out_trade_no = orders.getOrderId();
        String total_amount = String.valueOf(carPrice);
        String subject = String.valueOf(orders.getCarId());
        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html;charset=utf-8");
        System.out.println(result);
        response.getWriter().println(result);
    }

    @GetMapping("/paySuccess")
    public String paysuccess(HttpServletRequest request, Model model, HttpSession session) throws UnsupportedEncodingException, AlipayApiException {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        String orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        Map<String, Object> orders = transactionService.findOrderById(orderId);
        model.addAttribute("orders", orders);
        return "orderMsg.html";
    }
}
