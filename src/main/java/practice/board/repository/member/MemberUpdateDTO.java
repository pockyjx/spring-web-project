package practice.board.repository.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import practice.board.domain.member.Grade;

@Getter
@Setter
@ToString
public class MemberUpdateDTO {
    private String userName;
    private String password;
    private String email;
}
