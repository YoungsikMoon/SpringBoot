package com.mys.tutorial1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 개발자가 스프링부트한테 말한다
// 해당 클래스는 컨트롤러야
// 이 클래스는 웹 요청을 받아서 작업할 거야
public class HomeController {
    int num;

    public HomeController(){
        //Controller 어노테이션이 들어가면 알아서 객체 생성되기 때문에 바로 쓸 수 있다.
        num = -1;
    }

    @GetMapping("/home/main")
    // 개발자가 스프링부트한테 말한다
    // 만약에 '/home/main'  요청이 들어오면 아래 매서드를 실행시켜줘
    @ResponseBody
    // 아래 메서드를 실행한 후 리턴값을 응답으로 삼아서 body에 출력해줘
    public String showHome(){
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showHome2(){
        return "환영합니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showHome3(){
        return "스프링부트는 획기적입니다.";
    }


    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease(){
        num++;
        return num;
        // 우리는 숫자라 했지만 스프링부트에서 응답한 결과를 바디에 보내줄 때는 모든것이 문자열로 보여진다.
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, int b){
        return a+b;
        // 우리는 숫자라 했지만 스프링부트에서 응답한 결과를 바디에 보내줄 때는 모든것이 문자열로 보여진다.
    }
}
