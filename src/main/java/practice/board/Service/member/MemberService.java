package practice.board.Service.member;

import practice.board.domain.member.Member;
import practice.board.repository.member.MemberSearchDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member saveMember(Member member);

    List<Member> memberList(MemberSearchDTO memberSearch);

    Optional<Member> findMember(String userId);
}
