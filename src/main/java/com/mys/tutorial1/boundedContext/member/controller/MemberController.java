package com.mys.tutorial1.boundedContext.member.controller;

import com.mys.tutorial1.base.rsData.RsData;
import com.mys.tutorial1.boundedContext.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    /*
    @GetMapping("/member/login")
    @ResponseBody
    public Map<String, Object> login(String username, String password){
        Map<String, Object> rsData = new LinkedHashMap<>(){{
            put("ResultCode", "S-1");
            put("msg", "%s님 환영합니다.".formatted(username));
        }};

        return rsData;
    }
     */

    private final MemberService memberService;

    public MemberController(){
        memberService = new MemberService();
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password){
        return  memberService.tryLogin(username, password);
    }
}
