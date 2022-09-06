package webproject.marieclaire;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import webproject.marieclaire.interceptor.BoardAuthenticationInterceptor;
import webproject.marieclaire.interceptor.LoginAuthenticationInterceptor;
import webproject.marieclaire.interceptor.LoginCheckInterceptor;
import webproject.marieclaire.service.BoardService;
import webproject.marieclaire.service.MemberService;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;
    private final BoardService boardService;

    /*Interceptor*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        TODO 이거 꼭 이렇게 주입해줘야 할까?

        /*LoginCheckInterceptor*/
        registry.addInterceptor(new LoginCheckInterceptor(memberService))
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/member/login", "/member/join", "/css/**", "/img/**",
                "/board", "/images/**",
                "/board/view", "/board/list/**");

//        /*LoginAuthenticationInterceptor*/
        registry.addInterceptor(new LoginAuthenticationInterceptor(memberService))
            .order(2)
            .addPathPatterns("/**");

        /*AuthenticationInterceptor*/
        registry.addInterceptor(new BoardAuthenticationInterceptor(memberService, boardService))
            .order(3)
            .addPathPatterns("/board/view");
//

    }
}
