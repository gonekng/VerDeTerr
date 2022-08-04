package com.board.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * PasswordEncoder를 Bean으로 등록
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인증 or 인가에 대한 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("이거 컨피그 씀?");
		http.csrf().disable() // post 방식으로 값을 전송할 때 token을 사용해야하는 보안 설정을 해제
		.authorizeRequests()
		.antMatchers("/login", "/logout", "/signup", "/mypage", "/identify", "/identify_proc", "/modify", "/modify_proc", "/main","/signup_proc","/login_proc", "/findId", "/findPw", "/findId_proc", "/findPw_proc", "/css/**", "/img/**", "/js/**" , "/scripts/**","/plugin/**", "/survey/surveylist.do", "/survey/surveyresult.do", "redirect:/survey/surveyresult.do", "/board/list.do","/board/write.do","/board/register.do","/board/view.do","/board/delete.do")
				.permitAll()
				.anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll();
	}

	/*
	 * //static 파일 권한 허용 설정
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().requestMatchers(PathRequest.toStaticResources().
	 * atCommonLocations()); }
	 */

}
