package practice.board.repository.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;

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
    }

    @Test
    void memberList() {
    }

    @Test
    void findMember() {
    }
}