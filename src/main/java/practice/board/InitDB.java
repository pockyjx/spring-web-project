package practice.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.board.repository.member.MemberRepository;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final MemberRepository memberRepository;


}
