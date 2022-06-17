package com.momo.member;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.momo.domain.Member;




// Authentication 객체에 저장할 수 있는 유일한 타입
public class PrincipalDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Member member;
	//	private Map<String, Object> attributes;

	// 일반 시큐리티 로그인시 사용
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	
	
	public Member getMember() {
		return member;
	}

	@Override
	public String getPassword() {
		return member.getMemPassword();
	}

	@Override
	public String getUsername() {
		return member.getMemEmail();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 잠김 여부 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료 여부 
		return true;
	}

	@Override
	public boolean isEnabled() { // 사용 가능한 계정인지 
		return true;
	}
	
	@Override // 권한 처리 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(()->{ return member.getMemRole();});
		return collet;
	}


	
}
