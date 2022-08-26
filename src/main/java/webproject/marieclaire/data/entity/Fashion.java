package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "fashion")
public class Fashion extends Board{

    private String topic;

}
