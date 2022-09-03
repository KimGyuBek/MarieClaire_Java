package webproject.marieclaire.data.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import webproject.marieclaire.data.entity.Board;
import webproject.marieclaire.data.entity.UploadFile;

@Data
@Getter
@ToString
public class BoardDto {

    private Long boardId;

    private String mainTitle;

    private String subTitle;

    private String subject;

    private String contents;

    private LocalDateTime uploadTime;

    private MemberDto editor;
    /* TODO memberDto 전체를 넘겨주면 보안상 문제가 되지 않을까?*/


    private MultipartFile multipartFile;

    private UploadFile uploadFile;

    private String topic;

    public void transferFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public BoardDto(Board board) {
        this.boardId = board.getId();
        this.mainTitle = board.getMainTitle();
        this.subTitle = board.getSubTitle();
        this.subject = board.getSubject();
        this.contents = board.getContents();
        this.uploadTime = board.getUploadTime();
        this.uploadFile = board.getUploadFile();
    }



    public BoardDto() {

    }


}
