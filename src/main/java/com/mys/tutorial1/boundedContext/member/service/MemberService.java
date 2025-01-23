package com.mys.tutorial1.boundedContext.member.service;

import com.mys.tutorial1.base.rsData.RsData;
import com.mys.tutorial1.boundedContext.member.dto.Member;
import com.mys.tutorial1.boundedContext.member.repository.MemberRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

// 테스트
// 아래 클래스는 Ioc 컨테이너에 의해 생사소멸이 결정된다.
@Service//@Component : @Service와 같은 의미, 가독성 때문에 이렇게 표기
@Data
public class MemberService {

    public final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public RsData tryLogin(String username, String password) {
        Member member = memberRepository.findByUserName(username);
        if(member == null){
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if(!member.getPassword().equals(password)){
            return RsData.of("F1","비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("S-1", "%s님 환영합니다.".formatted(username), member);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }
}
