package com.momo.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Member;


// JpaRepository 를 상속하면 자동 컴포넌트 스캔됨.
public interface MemberRepository extends JpaRepository<Member, Long>{
	// SELECT * FROM user WHERE username = ?1
	
	Optional<Member> findByMemEmail(String memEmail);
	
//	// SELECT * FROM user WHERE provider = ?1 and providerId = ?2
//	Optional<Member> findByProviderAndProviderId(String provider, String providerId);
	
	//중복 검사 
    boolean existsByMemEmail(String memEmail);
    boolean existsByMemNickname(String memNickname);

	Member findByMemNickname(String memNickname);
	Member findByMemId(Long memId);

//	Member updateMemPassword(Member memPassword);

    
    
   
    
    
   
    
	
}


