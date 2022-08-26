package webproject.marieclaire;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.service.MemberService;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        createMember("user1", "1234", "1234@gmail.com", "syumdang");


    }

    private void createMember(String userId, String pwd, String userEmail, String userName) {
        MemberDto member = new MemberDto();
        member.setUserId(userId);
        member.setPwd(pwd);
        member.setUserEmail(userEmail);
        member.setUserName(userName);

        memberService.join(member);
    }


}