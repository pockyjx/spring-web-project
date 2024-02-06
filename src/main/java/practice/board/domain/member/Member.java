package practice.board.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import practice.board.domain.board.Post;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Pattern(regexp = "^[a-z0-9]{5,15}$")
    private String userId;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!~_-]).{8,}$")
    private String password;
    @NotBlank
    private String userName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    public Member(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }
}
