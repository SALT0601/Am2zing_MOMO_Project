package com.momo.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.momo.common.util.pagination.Paging;
import com.momo.domain.Board;
import com.momo.domain.Comment;
import com.momo.domain.Member;
import com.momo.member.MemberCustomRepository;


@Service
public class AdminService {
	
	@Autowired
	private AdminMemberRepository adminMemberRepository;
	
	@Autowired
	private MemberCustomRepository memberCustomRepository;
	
	@Autowired
	private AdminBoardRepository adminBoardRepository;
	
	@Autowired AdminCommentRepository adminCommentRepository;
	
	//회원 전체 조회
	public Map<String,Object> findAllMembers(int page){
		
		int cntPerPage = 6;
		List<Member> members = adminMemberRepository
				.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "memId"))
				.getContent();
		
		Paging paging = Paging.builder()
				.url("/admin/member/member-list")
				.total((int)adminMemberRepository.count())
				.cntPerPage(cntPerPage)
				.curPage(page)
				.blockCnt(10)
				.build();
		
		return Map.of("members",members,"paging",paging);
	}
	
	//해당 회원 조회
	public Member findMemberByMemNum(long memId) {
		Member member = adminMemberRepository.findById(memId).get();
		
		return member;
	}
	
	public Map<String, Object> findAllBoards(int page){
		int cntPerPage = 6;
		List<Board> boards = adminBoardRepository
				.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "BoardNum"))
				.getContent();
		
		Paging paging = Paging.builder()
				.url("/admin/board/board-list")
				.total((int)adminBoardRepository.count())
				.cntPerPage(cntPerPage)
				.curPage(page)
				.blockCnt(10)
				.build();
		
		return Map.of("boards",boards,"paging",paging);
		
	}
	
	//댓글 전체 조회
		public Map<String,Object> findAllComments(int page){
			
			int cntPerPage = 6;
			List<Comment> comments = adminCommentRepository
					.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "commentNum"))
					.getContent();
			
			Paging paging = Paging.builder()
					.url("/admin/comment/comment-list")
					.total((int)adminCommentRepository.count())
					.cntPerPage(cntPerPage)
					.curPage(page)
					.blockCnt(10)
					.build();
			
			return Map.of("comments",comments,"paging",paging);
		}
}
