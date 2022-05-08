package com.momo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberRepository;
import com.momo.place.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DumpBoard implements CommandLineRunner{

    private final BoardRepository boardRepository;
    
    private final MemberRepository memberRepository;
    
    private final PlaceRepository placeRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	String encPassword1 = bCryptPasswordEncoder.encode("1234");
    	
 	 	//랜덤데이터
 	   Member member1 = memberRepository.save(Member.builder()
 	            .memEmail("bbbb@bbbb.com")
 	            .memNickname("bbbb")
 	            .memMbti("INFP")
 	            .memPassword(encPassword1)
 	            .memBirth("2000-01-17")
 	            .memRole("ROLE_MEMBER")
 	            .build());
 	        
 	        Member member2 = memberRepository.save(Member.builder()
 	                .memEmail("cccc@cccc.com")
 	                .memNickname("cccc")
 	                .memMbti("ESFJ")
 	                .memPassword(encPassword1)
 	                .memBirth("1999-01-11")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        Member member3 = memberRepository.save(Member.builder()
 	                .memEmail("dddd@dddd.com")
 	                .memNickname("dddd")
 	                .memMbti("ENFP")
 	                .memPassword(encPassword1)
 	                .memBirth("1997-02-20")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        Member member4 = memberRepository.save(Member.builder()
 	                .memEmail("eeee@eeee.com")
 	                .memNickname("eeee")
 	                .memMbti("ENTP")
 	                .memPassword(encPassword1)
 	                .memBirth("1997-02-20")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        Member member5 = memberRepository.save(Member.builder()
 	                .memEmail("ffff@ffff.com")
 	                .memNickname("ffff")
 	                .memMbti("ESFJ")
 	                .memPassword(encPassword1)
 	                .memBirth("1993-02-1")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        Member[] member = {member1, member2, member3, member4, member5};
 	        
 	        for(int k=0;k<4;k++) {
 	           for(int i=1;i<9;i++) {
 	              Board board = boardRepository.save(Board.builder()
 	                 .boardTitle(i+"번")
 	                 .member(member[k])
 	                 .placeCnt(i*10)
 	                 .build());
 	              
 	              
 	              Place place = placeRepository.save(Place.builder()
 	                 .placeTitle("스타벅스 강남점")
 	                 .placeContent("조용해요")
 	                 .placeId("12345")
 	                 .placeLat("37.506229157876774")
 	                 .placeLng("127.05688880326076")
 	                 .board(board)
 	                 .member(member[k])
 	                 .build());
 	           } 
 	        }
 	     //member6
 	    	
 	    	Member member6 = memberRepository.save(Member.builder()
 	            .memEmail("Haha@naver.com")
 	            .memNickname("하핳")
 	            .memMbti("ENTP")
 	            .memPassword(encPassword1)
 	            .memBirth("1997-01-17")
 	            .memRole("ROLE_MEMBER")
 	            .build());
 	    	
 	    	 Board board1_mem6 = boardRepository.save(Board.builder()
 				        .boardTitle("ENTP 혼자놀기(강남편)")
 				        .member(member6)
 				        .placeCnt(105)
 				        .build());
 			        
 	    	
 	    	 Place place1_mem6 = placeRepository.save(Place.builder()
 		        		.placeTitle("스타벅스 강남점")
 		        		.placeContent("조용해요")
 		        		.placeId("12345")
 		        		.placeLat("37.506229157876774")
 		        		.placeLng("127.05688880326076")
 		        		.board(board1_mem6)
 		        		.member(member6)
 		        		.build());
 	    	 
 	     	 Place place2_mem6 = placeRepository.save(Place.builder()
 		        		.placeTitle("쉐이크쉑 강남점")
 		        		.placeContent("완전 맛있어요!!")
 		        		.placeId("12346")
 		        		.placeLat("37.50283878371788")
 		        		.placeLng("127.02561576880339")
 		        		.board(board1_mem6)
 		        		.member(member6)
 		        		.build());
 	     	 
 	     	 
 	     	 Place place3_mem6 = placeRepository.save(Place.builder()
 		        		.placeTitle("CGV 강남")
 		        		.placeContent("쉑쉑이랑 가까워서 버거 먹고 오기 딱 좋음")
 		        		.placeId("12347")
 		        		.placeLat("37.50160874231742")
 		        		.placeLng("127.02638154718464")
 		        		.board(board1_mem6)
 		        		.member(member6)
 		        		.build());
 	 	
 	    	
 	    	
 	    	
 	        //member7
 	        Member member7 = memberRepository.save(Member.builder()
 	                .memEmail("chanchan@gmail.com")
 	                .memNickname("찬찬")
 	                .memMbti("ESFJ")
 	                .memPassword(encPassword1)
 	                .memBirth("1995-05-11")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        
 	        Board board1_mem7 = boardRepository.save(Board.builder()
 			        .boardTitle("ESFJ는 산이 좋다 ㅎ")
 			        .member(member7)
 			        .placeCnt(12)
 			        .build());
 		        
 		
 		 Place place1_mem7 = placeRepository.save(Place.builder()
 	        		.placeTitle("북한산국립공원")
 	        		.placeContent("오늘은 북한산이지")
 	        		.placeId("12348")
 	        		.placeLat("37.67063083363566")
 	        		.placeLng("126.99060061305886")
 	        		.board(board1_mem7)
 	        		.member(member7)
 	        		.build());
 		 
 	 	 Place place2_mem7 = placeRepository.save(Place.builder()
 	        		.placeTitle("북한산둘레길 1구간소나무숲길")
 	        		.placeContent("이번엔 맛보기로 둘레길만 ㅋ")
 	        		.placeId("12349")
 	        		.placeLat("37.65902623714981")
 	        		.placeLng("127.00891626951096")
 	        		.board(board1_mem7)
 	        		.member(member7)
 	        		.build());
 	 	 
 	 	 

 		
 	        
 	        //member8
 	        Member member8 = memberRepository.save(Member.builder()
 	                .memEmail("UJ1122@naver.com")
 	                .memNickname("자라")
 	                .memMbti("ENFP")
 	                .memPassword(encPassword1)
 	                .memBirth("1998-06-20")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        
 	        
 	        Board board1_mem8 = boardRepository.save(Board.builder()
 			        .boardTitle("ENFP의 경주월드 투어")
 			        .member(member8)
 			        .placeCnt(125)
 			        .build());
 		        
 		
 		 Place place1_mem8 = placeRepository.save(Place.builder()
 	        		.placeTitle("경주월드")
 	        		.placeContent("가즈아! 경주월드")
 	        		.placeId("12360")
 	        		.placeLat("35.836224720874505")
 	        		.placeLng("129.2820618722431")
 	        		.board(board1_mem8)
 	        		.member(member8)
 	        		.build());
 		 
 	 	 Place place2_mem8 = placeRepository.save(Place.builder()
 	 			.placeTitle("경주월드 매표소")
 	    		.placeContent("여기가 바로 입구!")
 	    		.placeId("12361")
 	    		.placeLat("35.836027644338444")
 	    		.placeLng("129.2836606921457")
 	        		.board(board1_mem8)
 	        		.member(member8)
 	        		.build());
 	 	 
 	 	 Place place3_mem8 = placeRepository.save(Place.builder()
 	     		.placeTitle("경주월드리조트 어뮤즈먼트")
 	     		.placeContent("여기가 바로 놀이기구 천지!!")
 	     		.placeId("12347")
 	     		.placeLat("35.83661948715989")
 	     		.placeLng("129.28025292949096")
 	     		.board(board1_mem8)
 	     		.member(member8)
 	     		.build());
 		
 	        
 	        
 	        //member9
 	        Member member9 = memberRepository.save(Member.builder()
 	                .memEmail("minming@kakao.com")
 	                .memNickname("미믹")
 	                .memMbti("ISTJ")
 	                .memPassword(encPassword1)
 	                .memBirth("1993-09-20")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        Board board1_mem9 = boardRepository.save(Board.builder()
 			        .boardTitle("ISTJ 추천 장소.")
 			        .member(member9)
 			        .placeCnt(15)
 			        .build());
 		        
 		
 		 Place place1_mem9 = placeRepository.save(Place.builder()
 	        		.placeTitle("경복궁")
 	        		.placeContent("고궁.")
 	        		.placeId("12375")
 	        		.placeLat("37.577629045564755")
 	        		.placeLng("126.97684635430484")
 	        		.board(board1_mem9)
 	        		.member(member9)
 	        		.build());
 		 
 	 	 Place place2_mem9 = placeRepository.save(Place.builder()
 	 			.placeTitle("경복궁역 3호선")
 	    		.placeContent("여기서 내림.")
 	    		.placeId("12376")
 	    		.placeLat("37.5759525116974")
 	    		.placeLng("126.97358658721303")
 	        		.board(board1_mem9)
 	        		.member(member9)
 	        		.build());
 	 	 

 	    //member10
 	        Member member10 = memberRepository.save(Member.builder()
 	                .memEmail("heehee@gmail.com")
 	                .memNickname("쭈희")
 	                .memMbti("INFP")
 	                .memPassword(encPassword1)
 	                .memBirth("1997-10-01")
 	                .memRole("ROLE_MEMBER")
 	                .build());
 	        
 	        
 	        Board board1_mem10 = boardRepository.save(Board.builder()
 			        .boardTitle("INFP는 대구 대구좋아")
 			        .member(member10)
 			        .placeCnt(105)
 			        .build());
 		        
 		
 		 Place place1_mem10 = placeRepository.save(Place.builder()
 	        		.placeTitle("대구E월드")
 	        		.placeContent("두근두근 세근세근")
 	        		.placeId("12400")
 	        		.placeLat("35.8532626954799")
 	        		.placeLng("128.56651725483854")
 	        		.board(board1_mem10)
 	        		.member(member10)
 	        		.build());
 		 
 	 	 Place place2_mem10 = placeRepository.save(Place.builder()
 	 			.placeTitle("스파크랜드")
 	    		.placeContent("앞에 쉐이크쉑 있음")
 	    		.placeId("123401")
 	    		.placeLat("35.86876613919595")
 	    		.placeLng("128.59872155570434")
 	        		.board(board1_mem10)
 	        		.member(member10)
 	        		.build());
 	 	 
    }
}