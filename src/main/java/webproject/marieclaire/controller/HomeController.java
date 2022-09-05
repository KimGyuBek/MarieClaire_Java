package webproject.marieclaire.controller;

import java.net.MalformedURLException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webproject.marieclaire.data.dto.BoardDto;
import webproject.marieclaire.data.entity.UploadFile;
import webproject.marieclaire.file.FileStore;
import webproject.marieclaire.service.BoardService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final FileStore fileStore;

//    @RequestMapping("/")
//    public String home(Model model) {
//        model.addAttribute("boardForm", boardService.findBoard("user1"));
//
//        return "home";
//    }

    @RequestMapping("/")
    public String home() {

//        TODO home.html 백업본 로딩
        return "home_copy";
    }

    /*image 조회*/
    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable("fileName") String fileName)
        throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(fileName));
    }

}
