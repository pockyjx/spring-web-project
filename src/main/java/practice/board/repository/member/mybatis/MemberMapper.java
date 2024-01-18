package practice.board.repository.member.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void addMember(Member member);
    void updateMember(@Param("userId") String userId, @Param("updateDTO") MemberUpdateDTO updateDTO);
    List<Member> memberList(@Param("memberSearch") MemberSearchDTO memberSearch, @Param("grade") String grade);
    Optional<Member> findMember(String userId);

}
