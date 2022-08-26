package webproject.marieclaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/board-list")
@RestController
public class MenuController {

    @GetMapping("/fashion")
    public String fashion() {
        return "fashion list";
    }

}
