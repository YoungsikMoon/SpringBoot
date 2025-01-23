package com.mys.tutorial1.boundedContext.member.controller;

import com.mys.tutorial1.base.rq.Rq.Rq;
import com.mys.tutorial1.base.rsData.RsData;
import com.mys.tutorial1.boundedContext.member.dto.Member;
import com.mys.tutorial1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@AllArgsConstructor
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

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password, HttpServletRequest req , HttpServletResponse resp){
        Rq rq = new Rq(req, resp);

        if(username == null || username.trim().isEmpty()){
            return RsData.of("F-3","username을 입력해주세요.");
        }

        if(password == null || password.trim().isEmpty()){
            return RsData.of("F-4","비밀번호를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);

        if(rsData.isSuccess()){
            Member member = (Member) rsData.getData();
            rq.setCookie("loginedMemberId", member.getId());
        }
        return  rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);

        boolean cookieRemoved = rq.removeCookie("loginedMemberId");

        if(!cookieRemoved) {
            return RsData.of("F-1", "이미 로그아웃 상태입니다.");
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }


    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(HttpServletRequest req, HttpServletResponse resp){
        Rq rq = new Rq(req, resp);

        long loginedMemberId =rq.getCookieAsLong("LoginedMemberId", 0);

        boolean isLogined = loginedMemberId > 0; // loginedMemberId가 0보다 크면 로그인 상태로 간주


        if(!isLogined){
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member member = memberService.findById(loginedMemberId);

        return RsData.of("S-1", "당신의 username(은)는 \"%s\" 입니다.".formatted(member.getUsername()));
    }

}
