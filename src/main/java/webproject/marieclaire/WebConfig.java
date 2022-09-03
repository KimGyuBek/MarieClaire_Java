package webproject.marieclaire;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import webproject.marieclaire.interceptor.LoginCheckInterceptor;
import webproject.marieclaire.service.MemberService;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;

    /*로그인 Interceptor*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        TODO 이거 꼭 이렇게 주입해줘야 할까?
        registry.addInterceptor(new LoginCheckInterceptor(memberService))
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/member/login", "/css/**", "/img/**", "/board", "/images/**",
                "/board/view", "/board/list/**");


    }
}
