package practice.board.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.board.domain.board.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
