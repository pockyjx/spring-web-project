package practice.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.board.Service.member.MemberServiceImpl;
import practice.board.domain.member.Member;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceImpl service;

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
    String memberList(Model model) {
        model.addAttribute("members", service.memberList());
        return "members/memberList";
    }
}
