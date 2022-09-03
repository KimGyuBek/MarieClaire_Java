package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@DiscriminatorValue(value = "beauty")
public class Beauty extends Board{

    private String topic;

    public Beauty() {
        super();
    }

    public Beauty(BoardDto boardDto) {
        super(boardDto);
    }
}
