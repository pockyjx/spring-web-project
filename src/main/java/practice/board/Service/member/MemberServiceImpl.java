package practice.board.Service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberRepository;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.addMember(member);
    }

    @Override
    public void updateMember(String userId, MemberUpdateDTO memberUpdate) {
        memberRepository.updateMember(userId, memberUpdate);
    }

    @Override
    public List<Member> memberList(MemberSearchDTO memberSearch) {
        return memberRepository.memberList(memberSearch);
    }

    @Override
    public Optional<Member> findMember(String userId) {
        return memberRepository.findMember(userId);
    }

    @Override
    public Member login(String userId, String password) {
        Member member = findMember(userId).orElse(null);
        log.info("login member={}", member);
        if(member == null) return null;


        if(member.getPassword().equals(password)) return member;
        else return null;
    }
}
