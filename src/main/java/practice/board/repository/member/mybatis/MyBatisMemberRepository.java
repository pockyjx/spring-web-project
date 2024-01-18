package practice.board.repository.member.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberRepository;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member addMember(Member member) {
        memberMapper.addMember(member);
        member.setGrade(Grade.MEMBER);
        return member;
    }

    @Override
    public void updateMember(String userId, MemberUpdateDTO updateDTO) {
        memberMapper.updateMember(userId, updateDTO);
    }

    @Override
    public List<Member> memberList(MemberSearchDTO memberSearch) {
        String grade = (memberSearch.getGrade() != null) ? memberSearch.getGrade().name() : "";
        return memberMapper.memberList(memberSearch, grade);
    }

    @Override
    public Optional<Member> findMember(String userId) {
        return memberMapper.findMember(userId);
    }
}
