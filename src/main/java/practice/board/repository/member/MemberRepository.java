package practice.board.repository.member;

import practice.board.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원가입
    Member addMember(Member member);

    // 회원 수정
    void updateMember(Long id, MemberUpdateDTO updateDTO);

    // 회원 목록
    List<Member> memberList();

    // 회원 조회 (id)
    Optional<Member> findMember(String userId);

}
