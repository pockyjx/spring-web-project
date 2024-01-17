package practice.board.repository.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert simpleInsert;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.simpleInsert = new SimpleJdbcInsert(dataSource)
                                    .withTableName("member")
                                    .usingGeneratedKeyColumns("id")
                                    .usingColumns("user_id", "password", "user_name", "email");
    }

    @Override
    public Member addMember(Member member) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(member);
        Number key = simpleInsert.executeAndReturnKey(param);
        member.setId(key.longValue());
        member.setGrade(Grade.MEMBER);

        log.info("addMember={}", member);
        return member;
    }

    @Override
    public void updateMember(Long id, MemberUpdateDTO updateDTO) {
    }

    @Override
    public List<Member> memberList() {
        return null;
    }

    @Override
    public Member findMember(Long id) {
        return null;
    }
}
