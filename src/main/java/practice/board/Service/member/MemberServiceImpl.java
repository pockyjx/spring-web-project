package practice.board.Service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberRepository;
import practice.board.repository.member.MemberSearchDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.addMember(member);
    }

    @Override
    public List<Member> memberList(MemberSearchDTO memberSearch) {
        return memberRepository.memberList(memberSearch);
    }

    @Override
    public Optional<Member> findMember(String userId) {
        return memberRepository.findMember(userId);
    }
}
