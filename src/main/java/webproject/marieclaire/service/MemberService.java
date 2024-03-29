package webproject.marieclaire.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.data.entity.Member;

@Service
public interface MemberService {

    public void join(MemberDto memberDto);

    public MemberDto login(String loginId, String password);

    public void logout(HttpSession session);

    //
    /*회원 조회*/
    void updateMember(MemberDto memberDto);

    Member findByUserId(String userId);

    /*dto로 회원 조회*/
    boolean findBySession(HttpSession session);
}
