package webproject.marieclaire.service;

import java.util.List;
import org.springframework.stereotype.Service;
import webproject.marieclaire.data.dto.BoardDto;

@Service
public interface BoardService {

    /*게시글 업로드*/
    public Long upload(BoardDto boardDto);


    /*board list*/
    BoardDto findBoard(String userId);

    BoardDto findById(Long id);

    List<BoardDto> findAll();

    /*수정*/
    void update(BoardDto boardDto);

    /*삭제*/
    void delete(Long boardId);
}
