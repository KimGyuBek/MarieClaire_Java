package webproject.marieclaire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import webproject.marieclaire.SessionConst;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.service.MemberService;
import webproject.marieclaire.validation.member.MemberUpdateCheck;

/**
 * className : MemberController 로그인 및 회원 관련 기능 컨트롤러
 */

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    /*loginForm으로 이동*/
    @GetMapping("login")
    public String loginForm(@ModelAttribute("loginForm") MemberDto memberDto) {

        return "member/loginForm";
    }

    /*로그인 */
    @PostMapping("/login")
    public String login(@Validated() @ModelAttribute("loginForm") MemberDto memberDto,
        BindingResult bindingResult,
        HttpServletRequest request, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "member/loginForm";
        }

        MemberDto loginMember = memberService.login(memberDto.getUserId(), memberDto.getPwd());
        log.info("memberDto={}", memberDto.toString());

//        Session 처리
        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        redirectAttributes.addAttribute("login", true);

        return "redirect:/";
    }

    /*로그아웃*/
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        memberService.logout(request.getSession());
//        String requestURI = request.getRequestURI();

        return "redirect:/";
    }

    /*회원정보 수정페이지*/
    @GetMapping("/edit")
    public String updateForm(HttpServletRequest request, Model model) {

        MemberDto member = (MemberDto) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("[edit] member={}", member.toString());

        model.addAttribute("memberForm", member);

        return "member/memberEditForm";
    }

    /*회원정보 수정*/
    @PostMapping("/edit")
    public String update(
        @Validated(MemberUpdateCheck.class) @ModelAttribute("memberForm") MemberDto memberDto) {
        memberService.updateMember(memberDto);
        /*TODO 비밀번호 일치 확인*/
        return "redirect:/";
    }

    /**
     * 회원가입
     */

    /*회원 가입 form*/
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("joinForm") MemberDto memberDto) {
        return "member/joinForm";
    }

    /*회원가입*/
    @PostMapping("join")
    public String join(@Validated @ModelAttribute("joinForm") MemberDto memberDto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        try {
            memberService.join(memberDto);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
