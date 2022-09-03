package webproject.marieclaire.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import webproject.marieclaire.data.entity.Topics;

@Data
@AllArgsConstructor
public class Topic {

    private String code;
    private Topics displayName;



}
