package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "celeh")
public class Celeb extends Board{

    private String topic;

}
