package com.momo.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.momo.domain.Member;




@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
		
	        Optional<Member> memberWrapper = memberRepository.findByMemEmail(memEmail);
	        Member member = memberWrapper.get();
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority(member.getMemRole()));
	        return new PrincipalDetails(member);
	    }
	
	
	
		
	}


