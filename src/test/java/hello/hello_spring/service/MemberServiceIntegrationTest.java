package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //끝난후 롤백함.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void solution() {
        int[] arr = {1,1,3,3,0,1,1};
        Stack<Integer> intS = new Stack<>();
        for(int i : arr){
            intS.add(i);
        }

        Stack<Integer> newS = new Stack<>();
        int last = -1;
        while(!intS.empty()){
            System.out.println("last : "+last+", intS:"+ intS.toString()+", newS : "+newS.toString());

            if(last == -1){
                last = intS.pop();
            }else{
                if(last == intS.peek()){
                    if(newS.empty()){
                        newS.add(intS.pop());
                    }else if(newS.peek() == last){
                        intS.pop();
                    }else{
                        newS.add(intS.pop());
                    }

                }else{
                    last = intS.pop();
                }
            }

        }
        int[] result = new int[newS.size()];
        for(int k =0 ; k<newS.size(); k++){
            result[0]=newS.pop();
        }
        System.out.println("rrrrr>>>>"+result.toString());
    }
    @Test
    void join() {
    //given
        Member  member = new Member();
        member.setName("hello2");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void dup_member_exp() throws Exception{
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