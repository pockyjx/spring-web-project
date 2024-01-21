package practice.board.repository.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.board.Service.member.MemberServiceImpl;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberServiceImpl service;

    @Test
    void addMember() {
        Member member = new Member("test", "test1234!", "tester", "test@gmail.com");
        Member addMember = memberRepository.addMember(member);
        assertThat(member.getGrade()).isEqualTo(Grade.MEMBER);
    }

    @Test
    void updateMember() {
    }

    @Test
    void memberList() {
        Member member1 = new Member("test1", "test1234!", "tester1", "test1@gmail.com");
        Member member2 = new Member("test2", "test1234!", "tester2", "test2@gmail.com");
        Member member3 = new Member("abcde", "test1234!", "테스터", "test2@gmail.com");

        memberRepository.addMember(member1);
        memberRepository.addMember(member2);
        memberRepository.addMember(member3);

        test(null, null, null, member1, member2, member3);
        test("test", null, null, member1, member2);
        test(null, "tester", null, member1, member2);
        test(null, null, Grade.ADMIN);
        test("test", "tester2", null, member2);
    }

    @Test
    void findMember() {
        Member member1 = new Member("test1", "test1234!", "tester", "test1@gmail.com");
        Member member2 = new Member("test2", "test1234!", "tester", "test2@gmail.com");

        memberRepository.addMember(member1);
        memberRepository.addMember(member2);

        String userId = "test1";
        Member member = memberRepository.findMember(userId).get();

        assertThat(member.getUserId()).isEqualTo(member1.getUserId());
    }

    void test(String userId, String userName, Grade grade, Member... members) {
        List<Member> result = memberRepository.memberList(new MemberSearchDTO(userId, userName, grade));
        assertThat(result).containsExactly(members);
    }

    @Test
    void login() {
        Member member = new Member("test1", "test1234!", "tester", "test1@gmail.com");
        memberRepository.addMember(member);

        Member loginMember = service.login("test1", "test1234!");
        assertThat(member).isEqualTo(loginMember);
    }
}