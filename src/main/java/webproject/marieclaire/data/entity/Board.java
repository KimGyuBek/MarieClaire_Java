package webproject.marieclaire.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
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

    private String editor;
}
