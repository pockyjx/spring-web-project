package practice.board.repository.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void addMember() {
        Member member = new Member("test", "test1234!", "tester", "test@gmail.com");
        Member addMember = memberRepository.addMember(member);
        assertThat(member.getGrade()).isEqualTo(Grade.MEMBER);
    }

    @Test
    void updateMember() {
        Member member1 = new Member("test1", "test1234!", "tester", "test1@gmail.com");
        Member member2 = new Member("test2", "test1234!", "tester", "test2@gmail.com");
        memberRepository.addMember(member1);
        memberRepository.addMember(member2);

        assertThat(memberRepository.memberList().size()).isEqualTo(2);
    }

    @Test
    void memberList() {
        Member member1 = new Member("test1", "test1234!", "tester", "test1@gmail.com");
        Member member2 = new Member("test2", "test1234!", "tester", "test2@gmail.com");

        memberRepository.addMember(member1);
        memberRepository.addMember(member2);

        String userId = "test1";
        Member member = memberRepository.findMember(userId).get();

        assertThat(member.getUserId()).isEqualTo(member1.getUserId());
    }

    @Test
    void findMember() {
    }
}