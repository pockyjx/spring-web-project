package practice.board.repository.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
        String sql = "select * from member";
        List<Member> memberList = template.query(sql, getMemberRowMapper());
        return memberList;
    }

    @Override
    public Optional<Member> findMember(String userId) {
        Map<String, String> param = Map.of("userId", userId);
        String sql = "select * from member where user_id=:userId";

        try {
            Member member = template.queryForObject(sql, param, getMemberRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static RowMapper<Member> getMemberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }
}
