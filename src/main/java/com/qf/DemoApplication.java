package com.qf;

import com.qf.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class DemoApplication {

    @Value("${filepath}")
    String filepath;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Configuration
    public class MyConfig implements WebMvcConfigurer {
        //视图解析
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login.html");
            registry.addViewController("/firstpage").setViewName("firstPage.html");
            registry.addViewController("/user/toAddUser").setViewName("user/userAdd.html");
            registry.addViewController("/car_message/toAddCar_message").setViewName("car_message/car_messageAdd.html");
            registry.addViewController("/user/toUpdatePwd").setViewName("user/updatePwd.html");
            registry.addViewController("/all").setViewName("all-listings.html");
            registry.addViewController("/message").setViewName("message.html");
            registry.addViewController("/add").setViewName("add.html");
            registry.addViewController("/userMessage").setViewName("userMessage.html.html");
            registry.addViewController("/transaction/toUpdateUserPw").setViewName("updateUserPw.html");
            registry.addViewController("/news/toAddNews").setViewName("news/newsAdd.html");
            registry.addViewController("/all-news").setViewName("all-news.html");
            registry.addViewController("/seller/toAddSeller").setViewName("seller/sellerAdd.html");
        }

        //配置拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
            interceptorRegistration.addPathPatterns("/*");
            interceptorRegistration.excludePathPatterns("/login", "/user/login","login/register");
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/car/**").addResourceLocations("file:"+filepath);
        }
    }

    //拦截器
    public class MyInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            User login_user = (User) request.getSession().getAttribute("login_user");
           // 判断session是否为空
            if (login_user != null) {
                //不为空，已登录，放行
                return true;
            }
            //为空，未登录，进行拦截跳转登录页面
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
    }
}
