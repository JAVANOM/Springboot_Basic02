package com.fastcampus.ch2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    //@RequestMapping("/login/login" method=RequestMethod.GET)
    @GetMapping("/login")
    public String showlogin(){

        return "login";
    }
    //@RequestMapping("/login/login" method={RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/login")
    public String login(HttpServletRequest req, String id, String pwd, RedirectAttributes model) throws UnsupportedEncodingException {

        //1. id, pwd 확인
        if(loginCheck(id,pwd)){
            model.addAttribute("id", id);
            model.addAttribute("pwd", pwd);
            return "userInfo"; // userInfo.html
            } else {

            //일치 하지 않으면, login.html로 이동
            //String msg = URLEncoder.encode("id 또는 pwd가 일치 하지 않습니다.","utf-8");
            String msg = "id 또는 pwd가 일치하지 않습니다.";
            model.addAttribute("msg",msg);
            model.addFlashAttribute("msg", "임시 메시지 입니다."); // session 객체 전달하고 지워짐
            req.setAttribute("msg","request에 저장된 msg");
            model.addAttribute("request",req.getAttribute("msg"));

            return "forward:/";
            // return "redirect:/login/login?msg="+msg; //GET
        }

    }
    private boolean loginCheck(String id, String pwd){

        return id.equals("asdf") && pwd.equals("1234");
    }
}
