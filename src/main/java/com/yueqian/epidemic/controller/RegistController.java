package com.yueqian.epidemic.controller;

import com.yueqian.epidemic.bean.UserInfo;
import com.yueqian.epidemic.service.UserService;
import com.yueqian.epidemic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class RegistController {
    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public String regist (UserInfo userInfo, Model model,HttpSession session){
        System.out.println(userInfo);


         userService.regist(userInfo);
       model.addAttribute("regiester",userInfo);

//        UserInfo regist = new UserInfo();

//        System.out.println("usercontroller"+ regist);
//     session.getAttribute("regist");
//
//      if(regist!=null){
//            // System.out.println("登陆成功");
//            //登录成功，记录用户的登陆状态；
//            session.setAttribute("register",userInfo);
//            //重定向
            return "redirect:/success.jsp";

//        }else{
//            //System.out.println("登陆失败");
//
//           //请求转发
//            model.addAttribute("msg","用户名/密码/昵称不能为空！");
//            return "regist";
//      }
    }
}
