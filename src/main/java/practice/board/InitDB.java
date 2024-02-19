package practice.board;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.board.Service.board.PostService;
import practice.board.domain.API.User;
import practice.board.domain.board.Category;
import practice.board.domain.member.Member;
import practice.board.repository.API.UserRepository;
import practice.board.repository.board.CategoryRepository;
import practice.board.repository.member.MemberRepository;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    void init() {
        initService.initDB();
        initService.initDB_api();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepository memberRepository;
        private final CategoryRepository categoryRepository;
        private final PostService postService;

        private final UserRepository userRepository;

        public void initDB() {

            // 회원
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

            // 게시글
            postService.save("제목1", "내용1", "tester", 1L);
            postService.save("제목2", "내용2", "admin", 3L);
            postService.save("제목3", "내용3", "admin", 5L);
            postService.save("제목4", "내용4", "tester", 3L);
            postService.save("제목5", "내용5", "pockyjx", 4L);

            postService.save("제목6", "내용6", "tester", 2L);
            postService.save("제목7", "내용7", "pockyjx", 6L);
            postService.save("제목8", "내용8", "admin", 5L);
            postService.save("제목9", "내용9", "tester", 6L);
            postService.save("제목10", "내용10", "pockyjx", 2L);
        }


        public void initDB_api() {
            userRepository.save(new User("user1", 10, "user1"));
            userRepository.save(new User("user2", 20, "user2"));
            userRepository.save(new User("user3", 30, "user3"));
            userRepository.save(new User("user4", 40, "user4"));
            userRepository.save(new User("user5", 10, "user5"));
        }
    }

}
