package com.momo.member;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.momo.domain.Member;
import com.momo.domain.QMember;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RequiredArgsConstructor
@Repository
public class MemberCustomRepository {
	
	 private final JPAQueryFactory queryFactory;
	
	 public Member findByMemEmail(String memEmail) {
	        return queryFactory.selectFrom(QMember.member)
	                .where(QMember.member.memEmail.eq(memEmail))
	                .fetchOne();
	    }


    //본인 제외한 닉네임 중복 검사
   public List<String> findExistMemNickname(Long memId){
	   return queryFactory.select(QMember.member.memNickname)
			   .from(QMember.member)
			   .where(QMember.member.memId.ne(memId))
			   .fetch();
    }
   
   //본인것 제외한 닉네임 찾기
   public QueryResults<String> findExistNickname(Long id) {
       return queryFactory.select(QMember.member.memNickname)
               .from(QMember.member)
               .where(QMember.member.memId.ne(id))
               .fetchResults();
   }

    /*회원정보 수정*/
    @Transactional
    public void updateMember(Member member) {
        queryFactory.update(QMember.member)
                .set(QMember.member.memId, member.getMemId())
                .set(QMember.member.memEmail, member.getMemEmail() )
                .set(QMember.member.memNickname, member.getMemNickname())
                .set(QMember.member.memBirth, member.getMemBirth())
                .set(QMember.member.memMbti, member.getMemMbti())
                .set(QMember.member.memRole, member.getMemRole())
                .where(QMember.member.memId.eq(member.getMemId()))
                .execute();
    }

    @Transactional
    public void updateMemPassword(Member member) {
        queryFactory.update(QMember.member)
                .set(QMember.member.memPassword, member.getMemPassword())
                .where(QMember.member.memEmail.eq(member.getMemEmail()))
                .execute();
    }


}