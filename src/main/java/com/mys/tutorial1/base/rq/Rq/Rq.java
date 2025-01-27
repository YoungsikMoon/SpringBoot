package com.mys.tutorial1.base.rq.Rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public void setCookie(String name, long value) {
        setCookie(name,value + "");
    }
    public void setCookie(String name, String value) {
        resp.addCookie(new Cookie(name,value));
    }

    public boolean removeCookie(String name) {
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(name)) // 정확한 이름 사용
                    .forEach(cookie -> {
                        cookie.setMaxAge(0); // 쿠키 만료
                        resp.addCookie(cookie); // 수정된 쿠키를 응답에 추가
                    });
            return Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getName().equals(name));
        }
        return false;
    }

    public long getCookieAsLong(String name, long defaultValue) {
        String value = getCookie(name, null);

        if(value==null) return defaultValue;

        try{
            return Long.parseLong(value);
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }

    private String getCookie(String name, String defaultValue){
        if(req.getCookies() == null) return defaultValue;
        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(defaultValue);
    }
}
