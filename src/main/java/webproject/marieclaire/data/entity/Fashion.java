package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import webproject.marieclaire.data.dto.BoardDto;

@Entity
@DiscriminatorValue(value = "fashion")
public class Fashion extends Board{

    private String topic;

    public Fashion() {

    }

    public Fashion(BoardDto boardDto) {
        super(boardDto);
    }
}
