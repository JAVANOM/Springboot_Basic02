package com.fastcampus.ch2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

// 년 월일 입력 하면 요일을 얄러주는 원격 프로그램
@Controller
public class YoilTeller {
    @RequestMapping("/getYoil")
    public String main(@ModelAttribute("myDate") Mydate myDate, Model model) throws IOException {
        char yoil = getYoil(myDate);
        return "yoil";  //yoil.html
    }

    @ModelAttribute("yoil")
    private char getYoil(Mydate myDate) {
        Calendar cal = Calendar.getInstance(); // 현재날짜와 시간을 갖는 cal
        cal.clear(); //cal의 모든 필드를 초기화
        cal.set(myDate.getYear(), myDate.getMonth()-1, myDate.getDay()); // 월은 0부터 11이기 때문에 1을 빼줘야함

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1 ~ 7 반환
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); //0 ~ 6
        return yoil;
    }

    // requestmapping url과 일치하는 view (html) 요청 가능 - 정상 작동
    @RequestMapping("/yoil")
    public void main2(int year, int month, int day, Model model) throws IOException {
        //String year = request.getParameter("year");
        //String month = request.getParameter("month");
        //String day = request.getParameter("day");

        // int yyyy = Integer.parseInt(year);
        // int mm = Integer.parseInt(month);
        // int dd = Integer.parseInt(day);

        Calendar cal = Calendar.getInstance(); // 현재날짜와 시간을 갖는 cal
        cal.clear(); //cal의 모든 필드를 초기화
        cal.set(year, month-1, day); // 월은 0부터 11이기 때문에 1을 빼줘야함

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1 ~ 7 반환
        char yoil = "월화수목금토".charAt(dayOfWeek-1); //0 ~ 6

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);
    }

    @RequestMapping("/yoil2")
    public ModelAndView main3(int year, int month, int day) throws IOException {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("yoilError");
        
        if(!isValid(year, month, day)){
            return mv; // 사용자가 요청한 날짜가 유효하지 않으면 yoilError를 보여줌
        }

        Calendar cal = Calendar.getInstance(); // 현재날짜와 시간을 갖는 cal
        cal.clear(); //cal의 모든 필드를 초기화
        cal.set(year, month-1, day); // 월은 0부터 11이기 때문에 1을 빼줘야함

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //1 ~ 7 반환
        char yoil = "월화수목금토".charAt(dayOfWeek-1); //0 ~ 6

        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.addObject("day", day);
        mv.addObject("yoil", yoil);
        mv.setViewName("yoil");

        return mv;
    }

    public Boolean isValid(int year, int month, int day){
        return true;
    }

}
