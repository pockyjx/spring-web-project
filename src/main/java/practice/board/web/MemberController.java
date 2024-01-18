package practice.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    String addMember(@Validated @ModelAttribute Member member, BindingResult bindingResult,
                     RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult.getAllErrors());
            return "members/addMemberForm";
        }

        Member saveMember = service.saveMember(member);
        redirectAttributes.addAttribute("name", saveMember.getUserName());
        return "redirect:/";
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
                        BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult.getAllErrors());

            return "members/updateMemberForm";
        }

        service.updateMember(userId, memberUpdate);
        return "redirect:/members/{userId}";
    }
}
