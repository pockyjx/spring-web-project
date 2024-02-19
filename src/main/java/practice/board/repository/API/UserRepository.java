package practice.board.repository.API;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.board.domain.API.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
