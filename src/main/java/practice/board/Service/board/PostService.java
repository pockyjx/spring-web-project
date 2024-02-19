package practice.board.Service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.board.Category;
import practice.board.domain.board.Post;
import practice.board.domain.member.Member;
import practice.board.repository.board.CategoryRepository;
import practice.board.repository.board.PostRepository;
import practice.board.repository.member.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public Long save(String title, String content, String memberId, Long categoryId) {

        Member member = memberRepository.findMember(memberId).orElseThrow(IllegalStateException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(IllegalStateException::new);

        Post post = Post.builder().title(title).content(content).member(member).category(category).build();
        return postRepository.save(post).getId();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public Page<BoardDTO> findAll(Pageable pageable) {
        Page<Post> page = postRepository.findBoard(pageable);
        return page.map(board -> new BoardDTO(board));
    }
}
