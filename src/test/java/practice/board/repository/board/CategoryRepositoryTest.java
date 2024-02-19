package practice.board.repository.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.board.Category;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired CategoryRepository categoryRepository;
    @Autowired EntityManager em;

    @Test
    void createCategory() {
        Category parent = categoryRepository.save(Category.builder().name("parent").build());
        Category child = categoryRepository.save(Category.builder().name("child").parent(parent).build());

        assertThat(parent.getChild().get(0)).isEqualTo(child);
        assertThat(child.getParent().getId()).isEqualTo(parent.getId());
    }

    @Test
    void deleteCategory() {
        Category parent = categoryRepository.save(Category.builder().name("parent").build());
        Category child1 = categoryRepository.save(Category.builder().name("child1").parent(parent).build());

        categoryRepository.delete(parent);

        List<Category> categories = categoryRepository.findAll();
        assertThat(categories.size()).isEqualTo(0);
    }

}