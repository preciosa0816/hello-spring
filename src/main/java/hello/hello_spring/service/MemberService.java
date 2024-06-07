package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은이름 중복방지 1
//       Optional<Member> result = memberRepository.findByName(member.getName());
//       result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//       });
       // 같은이름 중복방지 2
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("member already exist");
        });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

}
