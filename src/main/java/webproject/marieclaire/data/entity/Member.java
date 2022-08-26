package webproject.marieclaire.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webproject.marieclaire.data.dto.MemberDto;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String pwd;

    private String userName;

    private String userEmail;

//    private int userLevel;


    protected Member() {
    }

    public Member(MemberDto memberDto) {
        this.userId = memberDto.getUserId();
        this.pwd = memberDto.getPwd();
        this.userName = memberDto.getUserName();
        this.userEmail = memberDto.getUserEmail();
    }
}
