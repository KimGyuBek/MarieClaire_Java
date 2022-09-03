package webproject.marieclaire.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@Table(name = "board")
public abstract class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;


    private String mainTitle;

    private String subTitle;

    private String subject;

    private String contents;

    private LocalDateTime uploadTime;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Member member;

    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board() {

    }

    public Board(BoardDto boardDto) {
        this.mainTitle = boardDto.getMainTitle();
        this.subTitle = boardDto.getSubTitle();
        this.subject = boardDto.getSubject();
        this.contents = boardDto.getContents();
        this.uploadTime = boardDto.getUploadTime();
        this.uploadFile = boardDto.getUploadFile();

    }

    public void updateBoard(BoardDto boardDto) {
        this.mainTitle = boardDto.getMainTitle();
        this.subTitle = boardDto.getSubTitle();
        this.subject = boardDto.getSubject();
        this.contents = boardDto.getContents();
        this.uploadTime = LocalDateTime.now();
        this.uploadFile = boardDto.getUploadFile();

    }

}
