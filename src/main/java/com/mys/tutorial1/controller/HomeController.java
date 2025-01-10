package com.mys.tutorial1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 개발자가 스프링부트한테 말한다
// 해당 클래스는 컨트롤러야
// 이 클래스는 웹 요청을 받아서 작업할 거야
public class HomeController {
    @GetMapping("/home/main")
    // 개발자가 스프링부트한테 말한다
    // 만약에 '/home/main'  요청이 들어오면 아래 매서드를 실행시켜줘
    @ResponseBody
    // 아래 메서드를 실행한 후 리턴값을 응답으로 삼아서 body에 출력해줘
    public String showHome(){
        return "안녕하세요";
    }
}
