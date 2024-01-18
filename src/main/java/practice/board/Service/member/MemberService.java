package practice.board.Service.member;

import practice.board.domain.member.Member;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member saveMember(Member member);

    void updateMember(String userId, MemberUpdateDTO memberUpdate);

    List<Member> memberList(MemberSearchDTO memberSearch);

    Optional<Member> findMember(String userId);

    Member checkPassword(String userId, String password);
}
