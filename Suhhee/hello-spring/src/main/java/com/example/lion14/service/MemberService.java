package com.example.lion14.service;

import com.example.lion14.domain.Member;
import com.example.lion14.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
/*
    // memory -> jdbc 로 변경할 경우 코드 하나하나 모두 수정해야 하는 번거로움이 있음
    private MemberRepository memberRepository = new MemberRepository();

    // 그래서 아래 코드처럼 수정할 경우 .. OCP를 위반하게 됨
    private MemberRepository memberRepository = new JdbcMemberRepository(); -> OCP 위반
 */
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
