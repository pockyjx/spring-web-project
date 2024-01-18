package practice.board.domain.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Member {

    private Long id;
    @Pattern(regexp = "^[a-z0-9]{5,15}$")
    private String userId;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!~_-]).{8,}$")
    private String password;
    @NotBlank
    private String userName;
    private String email;
    private Grade grade;

    public Member() {
    }

    public Member(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }
}
