package webproject.marieclaire.service.impl;

import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.data.entity.Member;
import webproject.marieclaire.data.repository.MemberRepository;
import webproject.marieclaire.service.MemberService;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입*/
    @Override
    @Transactional
    public void join(MemberDto memberDto) {
        Member member = new Member(memberDto);
        memberRepository.save(member);

        log.info("[memberService] join done!");
    }

    /*로그인*/
    @Override
    public MemberDto login(String loginId, String password) {
        Member findMember = memberRepository.findByUserId(loginId).orElseGet(null);
        if (findMember != null) {
            if (findMember.getPwd().equals(password)) {
                MemberDto memberDto = new MemberDto(findMember);

                return memberDto;
            }
        }
        throw new IllegalStateException("존재하지 않는 회원입니다.");

    }

    /*로그아웃*/
    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /*회원 수정*/
    @Override
    @Transactional
    public void updateMember(MemberDto memberDto) {

        Member updateMember = memberRepository.findByUserId(memberDto.getUserId())
            .orElseGet(null);

        updateMember.setPwd(memberDto.getPwd());
        updateMember.setUserEmail(memberDto.getUserEmail());

        memberRepository.save(updateMember);
    }
}
