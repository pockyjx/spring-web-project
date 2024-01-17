package practice.board.Service.member;

import practice.board.domain.member.Member;

import java.util.List;

public interface MemberService {
    Member saveMember(Member member);

    List<Member> memberList();
}
