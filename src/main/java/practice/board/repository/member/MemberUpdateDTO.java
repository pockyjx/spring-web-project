package practice.board.repository.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import practice.board.domain.member.Grade;

@Getter
@Setter
@ToString
public class MemberUpdateDTO {

    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    private String email;
}
