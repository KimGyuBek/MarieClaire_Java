package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "life")
public class Life extends Board{

    private String topic;

}
