package webproject.marieclaire.data.dto;


import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import webproject.marieclaire.data.entity.Member;
import webproject.marieclaire.validation.member.JoinCheck;
import webproject.marieclaire.validation.member.LoginCheck;
import webproject.marieclaire.validation.member.MemberUpdateCheck;

@Data
@ToString
public class MemberDto {

    private Long id;

    @NotBlank
    private String userId;

    @NotBlank(groups = {LoginCheck.class, JoinCheck.class, MemberUpdateCheck.class})
    private String pwd;

    @NotNull(groups = {JoinCheck.class, MemberUpdateCheck.class})
    private String pwdChk;

    @NotBlank(groups = {JoinCheck.class, MemberUpdateCheck.class})
    private String userName;

    @NotBlank(groups = {JoinCheck.class, MemberUpdateCheck.class})
    @Email
    private String userEmail;


    public MemberDto() {

    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.pwd = member.getPwd();
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
    }

    public MemberDto( String userId, String pwd, String userName, String userEmail) {
        this.userId = userId;
        this.pwd = pwd;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
