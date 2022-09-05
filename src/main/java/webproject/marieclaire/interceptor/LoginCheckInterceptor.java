package webproject.marieclaire.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import webproject.marieclaire.service.MemberService;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Autowired
    public LoginCheckInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

//        log.info("[loginCheckInterceptor]");

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        boolean sessionMember = memberService.findBySession(session);

        if (session == null || sessionMember == false) {
            response.sendRedirect("/member/login");

            log.info("[LoginCheckInterceptor] 미인증 사용자 요청");
            return false;
        }

        return true;
    }
}
