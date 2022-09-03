package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@DiscriminatorValue(value = "culture")
public class Culture extends Board{

    private String topic;

    public Culture() {
        super();
    }

    public Culture(BoardDto boardDto) {
        super(boardDto);
    }
}
