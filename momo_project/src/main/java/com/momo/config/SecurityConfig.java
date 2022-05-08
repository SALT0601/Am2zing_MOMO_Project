package com.momo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.momo.member.MemberLoginFail;
import com.momo.member.MemberLoginSuccess;
import com.momo.member.MemberService;
import com.momo.member.PrincipalDetailsService;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new MemberLoginSuccess();
    }
	@Bean
    public AuthenticationFailureHandler failureHandler() {
        return new MemberLoginFail();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
			.antMatchers("/member/login").permitAll()
			.antMatchers("/member/join").permitAll()
			.antMatchers("/member/emailChk").permitAll()
			.antMatchers("/member/nicknameChk").permitAll()
			.antMatchers("/member/**").access("hasRole('ROLE_MEMBER')or hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.failureUrl("/member/login?error=true")
			.usernameParameter("memEmail")
            .passwordParameter("memPassword")
            .successHandler(successHandler())
            .failureHandler(failureHandler())
            .permitAll()
		.and()
			.logout()
    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 시 URL 재정의 
    		.logoutSuccessUrl("/") // 로그아웃 성공 시 redirect 이동 
    		.invalidateHttpSession(true) // HTTP Session 초기화 
    		.deleteCookies("JSESSIONID") // 특정 쿠키 제거
    .and() // 403 예외처리 핸들링 
    	.exceptionHandling()
    		.accessDeniedPage("/denied")
    		
    .and()
			.csrf()
			.ignoringAntMatchers("/upload")
		    .ignoringAntMatchers("/placeList/*")
		    .ignoringAntMatchers("/deleteList/*")
		    .ignoringAntMatchers("/deletePlace/*")
		    .ignoringAntMatchers("/commentList/*")
		    .ignoringAntMatchers("/commentList02/*")
		    .ignoringAntMatchers("/deleteComment/*")
		    .ignoringAntMatchers("/boardBookmark")
		    .ignoringAntMatchers("/unBoardBookmark")
		    .ignoringAntMatchers("/post/*");

		    	 
		
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
    	//web security 예외처리 해당 url은 인증이 없어도 접근 가능
        web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**"); 
    }

}





