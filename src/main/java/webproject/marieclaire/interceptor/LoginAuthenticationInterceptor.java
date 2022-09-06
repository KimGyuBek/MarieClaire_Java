package webproject.marieclaire.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import webproject.marieclaire.service.MemberService;

public class LoginAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private final MemberService memberService;

    public LoginAuthenticationInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        boolean findSession = memberService.findBySession(request.getSession());

        if (findSession) {
            request.setAttribute("loginAuthentication", true);
        } else {
            request.setAttribute("loginAuthentication", false);
        }
    }
}
