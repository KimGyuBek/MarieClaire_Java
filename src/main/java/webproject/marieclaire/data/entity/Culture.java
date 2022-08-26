package webproject.marieclaire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "culture")
public class Culture extends Board{

    private String topic;

}
