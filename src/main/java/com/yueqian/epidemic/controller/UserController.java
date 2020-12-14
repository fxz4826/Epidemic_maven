package com.yueqian.epidemic.controller;

import com.yueqian.epidemic.bean.UserInfo;
import com.yueqian.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller    //当前类被springMVC管理
@RequestMapping("/user")  //给当前绑定一个请求路径
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(UserInfo userInfo, Model model,HttpSession session){
        System.out.println(userInfo);
        UserInfo loginedUser = userService.login(userInfo);

        System.out.println("usercontroller"+ loginedUser);
        if(loginedUser !=null){
           // System.out.println("登陆成功");
           //登录成功，记录用户的登陆状态；
          session.setAttribute("loginedUser",loginedUser);
            //重定向
            return "redirect:/main.jsp";

        }else{
            //System.out.println("登陆失败");

            //请求转发
            model.addAttribute("msg","用户名或密码有误！");
            return "login";
        }
    }
   @RequestMapping("/logout")
    public String logout(HttpSession session){
    //session.removeAttribute("loginedUser");
    session.invalidate();
    return "login";
    }



//    @RequestMapping("/registed")
//    public String registed(HttpSession session){
//        //session.removeAttribute("regist");
//        session.invalidate();
//        return "regist";
//    }
}
