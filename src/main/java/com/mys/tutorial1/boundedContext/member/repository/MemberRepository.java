package com.mys.tutorial1.boundedContext.member.repository;

import com.mys.tutorial1.boundedContext.member.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    List<Member> members;

    public MemberRepository(){
        members = new ArrayList<>();
        members.add(new Member("user1", "12341"));
        members.add(new Member("user2", "12342"));
        members.add(new Member("user3", "12343"));
        members.add(new Member("user4", "12344"));
        members.add(new Member("user5", "12345"));
        members.add(new Member("user6", "12346"));
        members.add(new Member("user7", "12347"));
        members.add(new Member("user8", "12348"));
        members.add(new Member("user9", "12349"));
    }

    public Member findByUserName(String username) {
        /*
        for(Member member:members){
            if(member.getUsername().equals(username)){
                return member;
            }else {
                return null;
            }
        }
        */
        return members.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public Member findById(long id) {
        return members.stream()
                .filter(member -> member.getId()==(id))
                .findFirst()
                .orElse(null);
    }
}
