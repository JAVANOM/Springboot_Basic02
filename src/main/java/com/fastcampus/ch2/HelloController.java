package com.fastcampus.ch2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

// 1. 원격프로그램 등록
// 2. url 과 메서드를 변경
@Controller
public class HelloController { //원격프로그램
    @RequestMapping("/")
    public String main(){

        return "index"; //  templates/index.html
    }

    @GetMapping("/test")
    public String test(Model model, HttpServletRequest request) {

        request.setAttribute("year", 2022); // request 객체에 저장

        HttpSession session = request.getSession(); // session 객체를 불러옴
        session.setAttribute("id", "test"); //session 객체에 id를 저장

        ServletContext application = session.getServletContext();
        application.setAttribute("email", "service@fastcampus.com");

        model.addAttribute("lastName", "hong");
        model.addAttribute("firstName", "Lee");
        model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        model.addAttribute("bno", "123");
        model.addAttribute("user", new User("aaa",234));
        return "test"; // template/test.html
    }
}
