package practice.board.repository.board;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.board.Post;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired PostRepository postRepository;

    @Test
    void findAllPaging() {
        PageRequest paging = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> page = postRepository.findAll(paging);

        int block = 5;
        int start = (int)(Math.ceil(((double)(page.getTotalPages()) / block)));
        int end = Math.min((start + block - 1), page.getTotalPages());

        System.out.println("start = " + start);
        System.out.println("end = " + end);

        System.out.println("--------------------------------------------------");

        System.out.println("page.getNumber() = " + page.getNumber());
        System.out.println("page.getNumberOfElements() = " + page.getNumberOfElements());
        System.out.println("page.getNumber() = " + page.getNumber());
        System.out.println("page.hasPrevious() = " + page.hasPrevious());
        System.out.println("page.getTotalPages() = " + page.getTotalPages());
    }

}