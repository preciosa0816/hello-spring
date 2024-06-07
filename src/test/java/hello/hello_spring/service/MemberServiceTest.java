package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //외부에서 넣어줌 dependency Injection (DI)

    }

    @AfterEach
    public void afterEach() { //memory clear
        memberRepository.clearStore();
    }



    @Test
    void join() {
        //given
        Member  member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void dup_member_exp(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

       /* try{
            memberService.join(member2);
            fail("");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("member already exist123");
        }*/
        //성공
        //assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //실패
        //assertThrows(NullPointerException.class, ()->memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member1));
        assertThat(e.getMessage()).isEqualTo("member already exist");


        //then



    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}