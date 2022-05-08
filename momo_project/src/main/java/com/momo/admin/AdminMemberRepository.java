package com.momo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Member;

public interface AdminMemberRepository extends JpaRepository<Member, Long>{

}
