package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "beauty")
public class Beauty extends Board{

    private String topic;

}
