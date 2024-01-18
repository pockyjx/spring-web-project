package practice.board.repository.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import practice.board.domain.member.Grade;

@Getter
@Setter
@ToString
public class MemberSearchDTO {
    private String userId;
    private String userName;
    private Grade grade;

    public MemberSearchDTO() {
    }

    public MemberSearchDTO(String userId, String userName, Grade grade) {
        this.userId = userId;
        this.userName = userName;
        this.grade = grade;
    }
}
