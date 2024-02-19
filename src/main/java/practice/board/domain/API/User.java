package practice.board.domain.API;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
@Schema(description = "회원 정보를 관리하기 위한 도메인 객체")
public class User {

    @Id @GeneratedValue
    @Schema(title = "사용자 id", description = "자동 생성")
    private Long id;

    @Schema(title = "사용자 이름", description = "2 ~ 15글자 이내")
    private String name;

    @Schema(title = "사용자 나이", description = "5세 이상부터 가입 가능")
    private int age;

    @JsonIgnore
    @Schema(title = "비밀번호", description = "8 ~ 20자 이내의 영소문자+숫자+특수기호(!~_=+@#) 조합")
    private String password;

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
