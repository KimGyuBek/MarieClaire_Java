package webproject.marieclaire.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import webproject.marieclaire.service.BoardService;
import webproject.marieclaire.service.MemberService;

@Slf4j
public class BoardAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final BoardService boardService;


    public BoardAuthenticationInterceptor(MemberService memberService, BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        String id = request.getParameter("id");
        log.info("[authenticationInterceptor] boardId={}", id);
        log.info("[authenticationInterceptor] modelAndView={}", modelAndView);

        boolean sessionMember = memberService.findBySession(request.getSession());
//        log.info("[authenticationInterceptor] sessionMember={}", sessionMember);

        if (sessionMember) {
            request.setAttribute("memberCheck", true);
//            log.info("[authenticationInterceptor] memberCheck");
        }


    }
}
