package webproject.marieclaire.service.impl;

import static webproject.marieclaire.data.entity.Topics.BEAUTY;
import static webproject.marieclaire.data.entity.Topics.CELEB;
import static webproject.marieclaire.data.entity.Topics.CULTURE;
import static webproject.marieclaire.data.entity.Topics.FASHION;
import static webproject.marieclaire.data.entity.Topics.LIFE;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webproject.marieclaire.data.dto.BoardDto;
import webproject.marieclaire.data.dto.MemberDto;
import webproject.marieclaire.data.entity.Beauty;
import webproject.marieclaire.data.entity.Board;
import webproject.marieclaire.data.entity.Celeb;
import webproject.marieclaire.data.entity.Culture;
import webproject.marieclaire.data.entity.Fashion;
import webproject.marieclaire.data.entity.Life;
import webproject.marieclaire.data.entity.Member;
import webproject.marieclaire.data.repository.BoardRepository;
import webproject.marieclaire.service.BoardService;
import webproject.marieclaire.service.MemberService;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;


    /*upload*/
    @Transactional
    @Override
    public void upload(BoardDto boardDto) {

        Board board = convertToEntity(boardDto);
        board.setMember(memberService.findByUserId(boardDto.getEditor().getUserId()));
        log.info("board={}", board.toString());

        boardRepository.save(board);
    }

    /*board list*/
    @Override
    public BoardDto findBoard(String userId) {
        Board findBoard = boardRepository.findByMember_UserId(userId);

        BoardDto boardDto = new BoardDto(findBoard);

        return boardDto;
    }

    /*id로 조회*/
    @Override
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseGet(null);

        BoardDto boardDto = new BoardDto(board);

        Member member = memberService.findByUserId(board.getMember().getUserId());
        MemberDto memberDto = new MemberDto(member);
        boardDto.setEditor(memberDto);

        return boardDto;
    }

    /*전체 조회*/
    @Override
    public List<BoardDto> findAll() {
        List<Board> findAll = boardRepository.findAll();
        List<BoardDto> result = findAll.stream()
            .map(b -> new BoardDto(b))
            .collect(Collectors.toList());

        return result;
    }

    /*수정*/
    @Transactional
    @Override
    public void update(BoardDto boardDto) {
        log.info("[boardservice] boardId={}", boardDto.getBoardId());
        Board board = boardRepository.findById(boardDto.getBoardId()).orElseGet(null);
        board.updateBoard(boardDto);
        boardRepository.save(board);
    }

    /*삭제*/
    @Transactional
    @Override
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseGet(null);

        log.info("[boardService] delete board={}", board.toString());
        if (board == null) {
            throw new IllegalStateException("삭제 오류");
        }

        boardRepository.delete(board);

        log.info("[boardService] 게시글 삭제완료 boardId={}", boardId);

    }

    private Board convertToEntity(BoardDto boardDto) {
        String topic = boardDto.getTopic();
        if (topic.equals(FASHION.getDescription())) {
            Board board = new Fashion(boardDto);
            return board;
        } else if (topic.equals(BEAUTY.getDescription())) {
            Board board = new Beauty(boardDto);
            return board;
        } else if (topic.equals(CELEB.getDescription())) {
            Board board = new Celeb(boardDto);
            return board;
        } else if (topic.equals(CULTURE.getDescription())) {
            Board board = new Culture(boardDto);
            return board;
        } else if (topic.equals(LIFE.getDescription())) {
            Board board = new Life(boardDto);
            return board;
        }
//        TODO 이거 약간 이상함

        throw new IllegalStateException("topic error!");
    }


}
