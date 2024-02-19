package practice.board.Service.board;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.board.Category;
import practice.board.domain.board.Post;
import practice.board.domain.member.Member;
import practice.board.repository.board.CategoryRepository;
import practice.board.repository.member.MemberRepository;

import javax.swing.text.html.parser.Entity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired PostService postService;
    @Autowired EntityManager em;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    @BeforeEach
    void before() {
        Member admin = memberRepository.addMember(Member.builder().
                userId("admin").password("test1234!").userName("관리자").email("admin@naver.com").build());

        Member tester = memberRepository.addMember(Member.builder().
                userId("tester").password("test1234!").userName("테스터").email("test1@naver.com").build());

        Member pockyjx = memberRepository.addMember(Member.builder().
                userId("pockyjx").password("test1234!").userName("정예진").email("pockyjx@naver.com").build());


        // 카테고리
        Category parent1 = categoryRepository.save(Category.builder().name("parent1").build());
        Category child1 = categoryRepository.save(Category.builder().name("child1").parent(parent1).build());
        Category child2 = categoryRepository.save(Category.builder().name("child2").parent(parent1).build());

        Category parent2 = categoryRepository.save(Category.builder().name("parent2").build());
        Category child3 = categoryRepository.save(Category.builder().name("child3").parent(parent2).build());
        Category child4 = categoryRepository.save(Category.builder().name("child4").parent(parent2).build());
    }


    @Test
    void save() {
        Long saveId = postService.save("title", "content", "tester", 1L);

        em.flush();
        em.clear();

        Post findPost = postService.findById(saveId);
        assertThat(findPost.getMember().getUserName()).isEqualTo("테스터");
        assertThat(findPost.getCategory().getName()).isEqualTo("parent1");

        assertThatThrownBy(() -> postService.save("title", "content", "exception", 1L))
                .isInstanceOf(IllegalStateException.class);

        assertThatThrownBy(() -> postService.save("title", "content", "admin", 1000L))
                .isInstanceOf(IllegalStateException.class);
    }

}