package practice.board.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import practice.board.domain.board.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.category c join fetch p.member m")
    Page<Post> findBoard(Pageable pageable);
}
