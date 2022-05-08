package com.momo.member;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import com.momo.domain.Member;

@Service
	public class MemberLoginSuccess implements AuthenticationSuccessHandler {

	    @Resource
	    private MemberService memberService;

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	       
	        RequestCache requestCache = new HttpSessionRequestCache();
	        SavedRequest savedRequest = requestCache.getRequest(request, response);

	       
	        String prevPage = (String) request.getSession().getAttribute("prevPage");

	        
	        String url = "/";


	      
	        if (savedRequest != null) {
	            url = savedRequest.getRedirectUrl();

	         
	        } else if (prevPage != null) {
	            url = prevPage;
	        }

	       
	        Member member = memberService.memberInfo(authentication.getName());

	        HttpSession session = request.getSession(false);
	        session.setAttribute("memId", member.getMemId());
	        session.setAttribute("memEmail", member.getMemEmail());
	        session.setAttribute("memNickname", member.getMemNickname());
	        session.setAttribute("memRole", member.getMemRole());

	        response.sendRedirect(url);
	    }
	}
