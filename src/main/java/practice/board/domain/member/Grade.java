package practice.board.domain.member;

public enum Grade {

    ADMIN("관리자"), MEMBER("일반 회원"), BLOCKED("정지 회원");

    String description;

    Grade(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
