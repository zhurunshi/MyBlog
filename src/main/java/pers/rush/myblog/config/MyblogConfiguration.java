package pers.rush.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pers.rush.myblog.interceptor.SessionInterceptor;
import pers.rush.myblog.interceptor.TokenInterceptor;

/**
 * springboot配置
 * @author Rush
 *
 */
@Configuration
public class MyblogConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// session拦截器
		registry.addInterceptor(new SessionInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login/login/login")
			.excludePathPatterns("/")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/js/**")
			.excludePathPatterns("/images/**")
			.excludePathPatterns("/fonts/**");
		// token拦截器
		registry.addInterceptor(new TokenInterceptor())
			.addPathPatterns("/**");
	}
}
