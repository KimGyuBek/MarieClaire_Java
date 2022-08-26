package webproject.marieclaire;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import webproject.marieclaire.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*로그인 Interceptor*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor())
//            .order(1)
//            .addPathPatterns("/**")
//            .excludePathPatterns("/", "/member/login", "/css/**", "/img/**");

    }
}
