package practice.board.repository.member.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberRepository;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class jpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    @Override
    public Member addMember(Member member) {
        member.setGrade(Grade.MEMBER);
        em.persist(member);
        return member;
    }

    @Override
    public void updateMember(String userId, MemberUpdateDTO updateDTO) {
        Member findMember = em.createQuery("select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId", userId).getSingleResult();

        findMember.setUserName(updateDTO.getUserName());
        findMember.setEmail(updateDTO.getEmail());
    }

    @Override
    public List<Member> memberList(MemberSearchDTO memberSearch) {

        String jpql = "select m from Member m";

        String userId = memberSearch.getUserId();
        String userName = memberSearch.getUserName();
        Grade grade = memberSearch.getGrade();

        if (StringUtils.hasText(userId) || StringUtils.hasText(userName) || grade != null) {
            jpql += " where";
        }
        boolean andFlag = false;

        if (StringUtils.hasText(userId)) {
            jpql += " m.userId like concat('%',:userId,'%')";
            andFlag = true;
        }

        if(StringUtils.hasText(userName)) {
            if (andFlag) {
                jpql += " and";
            }

            jpql += " m.userName like concat('%', :userName, '%')";
            andFlag = true;
        }

        if (grade != null) {
            if (andFlag) {
                jpql += " and";
            }
            jpql += " m.grade = :grade";
        }

        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        if (StringUtils.hasText(userId)) {
            query.setParameter("userId", userId);
        }

        if (StringUtils.hasText(userName)) {
            query.setParameter("userName", userName);
        }

        if (grade != null) {
            query.setParameter("grade", grade);
        }
        return query.getResultList();
    }

    @Override
    public Optional<Member> findMember(String userId) {

        List<Member> members = em.createQuery("select m from Member m where m.userId = :userId ", Member.class)
                .setParameter("userId", userId)
                .getResultList();
        if(!members.isEmpty()) return Optional.of(members.get(0));
        return Optional.empty();

        // userId가 기본키가 아니기 때문에 find 사용 불가 ㅠㅠ
        // return Optional.ofNullable(em.find(Member.class, userId));
    }
}
