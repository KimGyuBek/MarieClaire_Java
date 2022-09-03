package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@DiscriminatorValue(value = "life")
public class Life extends Board{

    private String topic;

    public Life() {
        super();
    }

    public Life(BoardDto boardDto) {
        super(boardDto);
    }
}
