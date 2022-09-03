package webproject.marieclaire.controller;

import static webproject.marieclaire.data.entity.Topics.BEAUTY;
import static webproject.marieclaire.data.entity.Topics.CELEB;
import static webproject.marieclaire.data.entity.Topics.CULTURE;
import static webproject.marieclaire.data.entity.Topics.FASHION;
import static webproject.marieclaire.data.entity.Topics.LIFE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webproject.marieclaire.SessionConst;
import webproject.marieclaire.data.dto.BoardDto;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.data.dto.Topic;
import webproject.marieclaire.data.entity.UploadFile;
import webproject.marieclaire.file.FileStore;
import webproject.marieclaire.service.BoardService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {


    private final FileStore fileStore;
    private final BoardService boardService;


    /*게시판 선택*/
    @ModelAttribute("topics")
    public List<Topic> topicList() {
        List<Topic> topics = new ArrayList<>();

        topics.add(new Topic(BEAUTY.getDescription(), BEAUTY));
        topics.add(new Topic(CELEB.getDescription(), CELEB));
        topics.add(new Topic(CULTURE.getDescription(), CULTURE));
        topics.add(new Topic(FASHION.getDescription(), FASHION));
        topics.add(new Topic(LIFE.getDescription(), LIFE));

        return topics;
    }


    /*게시글 리스트*/
    @GetMapping("/list/{topic}")
    public String boardList(@PathVariable("topic") String topic, Model model) {
        log.info("[boardList] topic={}", topic);
        List<BoardDto> boardForms = boardService.findAll();
        model.addAttribute("boardForms", boardForms);

        return "board/board_list";
    }

    /*boardForm */
    @GetMapping("/post")
    public String boardForm(@ModelAttribute("boardForm") BoardDto boardDto,
        HttpServletRequest request) {
        boardDto.setEditor(
            (MemberDto) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER));

        log.info("[get]boardDto={}", boardDto.toString());

        return "board/boardForm";
    }

    /*게시글 업로드*/
    @ResponseBody
    @PostMapping("/post")
    public String upload(@ModelAttribute("boardForm") BoardDto boardDto) {

//        upload 시간 설정
        boardDto.setUploadTime(LocalDateTime.now());

        UploadFile uploadFile = fileStore.storeFile(boardDto.getMultipartFile());
        boardDto.transferFile(uploadFile);

        log.info("[post]boardDto={}", boardDto.toString());

        boardService.upload(boardDto);

        return "ok";

    }

    /*board_view*/
    @GetMapping("/view")
    public String boardViewForm(@RequestParam("id") Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        log.info("[boardController] boardDto={}", boardDto);

        model.addAttribute("boardForm", boardDto);

        return "board/board_view";
    }

    /*boardForm_edit*/
    @GetMapping("/edit")
    public String editForm(@RequestParam("id") Long id, Model model) {
        BoardDto board = boardService.findById(id);
        model.addAttribute("boardForm", board);
        log.info("[boardController] editForm boardDto={}", board);

        return "board/boardForm_edit";
    }

    /*update board*/
    @PostMapping("/edit")
    public String edit(@ModelAttribute("boardForm") BoardDto boardDto,
        RedirectAttributes redirectAttributes) {
        log.info("[boardController] edit ={}", boardDto.toString());

//        TODO image를 안 올릴 수도 있음
        UploadFile uploadFile = fileStore.storeFile(boardDto.getMultipartFile());
        boardDto.transferFile(uploadFile);

        boardService.update(boardDto);

        redirectAttributes.addAttribute("id", boardDto.getBoardId());

        return "redirect:/board/view";

    }

    /*게시글 삭제*/
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);

        return "redirect:/board/list/fashion";

    }


}