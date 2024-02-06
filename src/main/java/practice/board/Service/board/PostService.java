package practice.board.Service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.board.Post;
import practice.board.repository.board.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public Page<BoardDTO> findAll(Pageable pageable) {
        Page<Post> page = postRepository.findBoard(pageable);
        return page.map(board -> new BoardDTO(board));
    }
}
