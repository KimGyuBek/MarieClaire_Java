package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@DiscriminatorValue(value = "celeh")
public class Celeb extends Board{

    private String topic;

    public Celeb() {
        super();
    }

    public Celeb(BoardDto boardDto) {
        super(boardDto);
    }
}
