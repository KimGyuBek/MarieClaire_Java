package webproject.marieclaire;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import webproject.marieclaire.data.dto.BoardDto;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.data.entity.Board;
import webproject.marieclaire.data.entity.Fashion;
import webproject.marieclaire.data.entity.Topics;
import webproject.marieclaire.service.BoardService;
import webproject.marieclaire.service.MemberService;

//@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;
    private final BoardService boardService;

    @PostConstruct
    public void init() {
        createMember("user1", "1234", "1234@gmail.com", "syumdang");

//        BoardDto boardDto = new BoardDto();
//        boardDto.setMainTitle("main");
//        boardDto.setSubTitle("sub");
//        boardDto.setSubject("subject");
//        boardDto.setContents("contents");
//        boardDto.setUploadTime(LocalDateTime.now());
//        boardDto.setFileDir("/");
//        boardDto.setTopic(Topics.BEAUTY.getDescription());
//        boardDto.setEditor(new MemberDto("user2", "1234", "1111@gmail.com",
//            "ddangddang"));
//
//        boardService.upload(boardDto);



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