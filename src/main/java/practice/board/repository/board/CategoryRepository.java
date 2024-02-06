package practice.board.repository.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.board.domain.board.Category;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    public void addCategory(Category category) {
        em.persist(category);
    }

    public void removeCategory(Category category) {
        em.remove(category);
    }
}
