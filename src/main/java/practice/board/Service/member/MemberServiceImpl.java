package practice.board.Service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.addMember(member);
    }
}
