package pers.rush.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pers.rush.myblog.interceptor.LoginInterceptor;

/**
 * springboot配置
 * @author Rush
 *
 */
@Configuration
public class MyblogConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login/login/login")
			.excludePathPatterns("/")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/js/**")
			.excludePathPatterns("/images/**")
			.excludePathPatterns("/fonts/**");
	}
}
