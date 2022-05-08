
package com.momo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.momo.domain.Member;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DumpMember implements CommandLineRunner{

    private final MemberRepository memberRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	String encPassword = bCryptPasswordEncoder.encode("admin");

                  Member member1 = memberRepository.save(Member.builder()
                    .memEmail("admin@admin.com")
                    .memNickname("admin")
                    .memPassword(encPassword)
                    .memRole("ROLE_ADMIN")
                    .build());
                    
        String encPassword1 = bCryptPasswordEncoder.encode("aaaa");
        Member member2 = memberRepository.save(Member.builder()
                .memEmail("aaaa@aaaa.com")
                .memNickname("aaaa")
                .memMbti("INFP")
                .memPassword(encPassword1)
                .memBirth("1995-01-17")
                .memRole("ROLE_MEMBER")
                .build());
                }
           }

