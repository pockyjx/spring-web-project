package practice.board.web.API;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import practice.board.domain.API.User;
import practice.board.exception.ExceptionResponse;
import practice.board.exception.UserNotFoundException;
import practice.board.repository.API.UserRepository;
import practice.board.web.member.MemberController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "회원 관리 컨트롤러")
public class UserController {
    private final UserRepository repository;

    @GetMapping
    @Operation(summary = "회원 목록 API", description = "전체 회원 목록을 조회")
    public UserDTO allUsers() {
        List<User> users = repository.findAll();
        return new UserDTO(users.size(), users);
    }

    @PostMapping
    @Operation(summary = "회원 등록 API", description = "새로운 회원을 등록")
    public ResponseEntity<User> save(@RequestBody User user) {
        User saveUser = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @GetMapping("/{id}")
    public User oneUser(@PathVariable Long id) {
        Optional<User> optional = repository.findById(id);
        if(optional.isEmpty()) throw new UserNotFoundException(id+"번 회원을 찾을 수 없습니다.");

        return optional.get();
    }

    @GetMapping("/{id}")
    @Operation(summary = "회원 조회 API", description = "사용자 ID를 통해 특정 회원 조회")
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200"),
            @ApiResponse(description = "NOT FOUND", responseCode = "404"),
            @ApiResponse(description = "INTERNAL SERVER ERROR", responseCode = "500")
    })
    public EntityModel<User> oneUser_HATEOAS(@Parameter(description = "사용자 ID", required = true, example = "1")
                                                 @PathVariable Long id) {
        Optional<User> optional = repository.findById(id);
        if(optional.isEmpty()) throw new UserNotFoundException(id+"번 회원을 찾을 수 없습니다.");
        User user = optional.get();

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder allUser = linkTo(methodOn(this.getClass()).allUsers());
        entityModel.add(allUser.withRel("all-user"));

        return entityModel;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원 삭제 API", description = "사용자 ID를 통해 특정 회원 삭제")
    public void deleteUser(@PathVariable Long id) {
        Optional<User> optional = repository.findById(id);
        if(optional.isEmpty()) throw new UserNotFoundException(id+"번 회원을 찾을 수 없습니다.");

        repository.deleteById(id);
    }

    @Data
    @AllArgsConstructor
    static class UserDTO {
        private int cnt;
        private List<User> users;
    }
}
