package practice.board.web.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.board.Const;
import practice.board.Service.member.MemberServiceImpl;
import practice.board.domain.member.Grade;
import practice.board.domain.member.Member;
import practice.board.repository.member.MemberSearchDTO;
import practice.board.repository.member.MemberUpdateDTO;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceImpl service;

    @ModelAttribute("grades")
    public Grade[] grade() {
        return Grade.values();
    }

    @GetMapping("/add")
    String addMemberForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    String addMember(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult.getAllErrors());
            return "members/addMemberForm";
        }

        Member saveMember = service.saveMember(member);
        return "redirect:/members/login";
    }

    @GetMapping
    String memberList(Model model, @ModelAttribute("memberSearch") MemberSearchDTO memberSearch) {
        model.addAttribute("members", service.memberList(memberSearch));
        return "members/memberList";
    }

    @GetMapping("/{userId}")
    String findMember(@PathVariable("userId") String userId, Model model) {

        Member member = service.findMember(userId).get();
        model.addAttribute("member", member);
        return "members/memberInfo";
    }

    @GetMapping("/update/{userId}")
    String updateMemberForm(@PathVariable("userId") String userId, Model model) {

        Member member = service.findMember(userId).get();
        model.addAttribute("member", member);
        return "members/updateMemberForm";
    }

    @PostMapping("/update/{userId}")
    String updateMember(@PathVariable("userId") String userId,
                        @Validated @ModelAttribute("member") MemberUpdateDTO memberUpdate,
                        BindingResult bindingResult, HttpServletRequest req) {

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult.getAllErrors());

            return "members/updateMemberForm";
        }

        service.updateMember(userId, memberUpdate);
        Member member = service.findMember(userId).get();

        HttpSession session = req.getSession();
        session.setAttribute(Const.LOGIN_SESSION_NAME, member);
        return "redirect:/members/{userId}";
    }

    @GetMapping("/login")
    String loginForm(@ModelAttribute("login") LoginDTO login) {
        return "members/login";
    }

    @PostMapping("/login")
    String login(@Validated @ModelAttribute("login") LoginDTO login, BindingResult bindingResult,
                 HttpServletRequest req) {

        Member member = service.login(login.getUserId(), login.getPassword());
        if(member == null) {
            bindingResult.reject("login", "아이디 혹은 비밀번호가 잘못되었습니다!");
            return "members/login";
        }

        HttpSession session = req.getSession();
        session.setAttribute(Const.LOGIN_SESSION_NAME, member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        session.invalidate();
        return "redirect:/";
    }
}
