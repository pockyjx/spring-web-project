package practice.board.web.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
