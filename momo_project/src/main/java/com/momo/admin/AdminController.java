package com.momo.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.board.BoardRepository;
import com.momo.board.BoardService;
import com.momo.bookmark.BoardBookMarkService;
import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.member.MemberService;
import com.momo.member.PrincipalDetails;
import com.momo.place.PlaceService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired 
	private MemberService memberService;
	
	@Autowired 
	private BoardBookMarkService boardBookmarkService;
	
	//전체 회원 조회
	@GetMapping("member/member-list")
	public void searchAllMembers(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllMembers(page));
	}
	
	//개별 회원 조회
	@GetMapping("member/member-detail/{memId}")
	public String showMemberDetail(@PathVariable Integer memId, Model model) {
		model.addAttribute("member", this.adminService.findMemberByMemNum(memId));
		return "admin/member/member-detail";
	}
	
	//회원 정보 수정
    @PostMapping("member/memberinfoEdit")
    public String memberUpdate(Member member) {
        memberService.updateMember(member);
        return "redirect:member-list";
    }
    
    //회원 탈퇴 실행
    @PostMapping("/member/memberwithdrawal")
    public String withdrawalMember(Member member) {
        memberService.deleteMember(member.getMemId());
        return "redirect:member-list";
    }
	
    //게시글 전체 조회
	@GetMapping("board/board-list")
	public void searchAllBoards(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllBoards(page));
	}
	
	//개별 게시글 조회
	@GetMapping("board/board-detail/{boardNum}")
	public String showboardDetail(@PathVariable Integer boardNum, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		Board board = boardService.getPost(boardNum);
		Member member = principal.getMember();
    	model.addAttribute("boardBookmarkCheck", boardBookmarkService.isBoardBookmarkChecked(member, (long) boardNum));
		model.addAttribute("board", board);
		model.addAttribute("places", placeService.getPlaceList(boardNum));
		return "Board/board_detail";
	}
	
	@DeleteMapping("board-delete/{boardNum}")
    public String delete(@PathVariable("boardNum") Long boardNum) {
		List<Board> boards = boardRepository.findByBoardNum(boardNum);
		for(Board board:boards) {
		boardService.deletePost(board);
		}
        return "redirect:/admin/board/board-list";
    }
	
	//전체 댓글 조회
	@GetMapping("comment/comment-list")
	public void searchAllComments(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllComments(page));
	}
	
	//개별 댓글 조회
	@GetMapping("comment/comment-detail/{boardNum}")
	public String showcommentDetail(@PathVariable Integer boardNum, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		Board board = boardService.getPost(boardNum);
		Member member = principal.getMember();
    	model.addAttribute("boardBookmarkCheck", boardBookmarkService.isBoardBookmarkChecked(member, (long) boardNum));
		model.addAttribute("board", board);
		model.addAttribute("places", placeService.getPlaceList(boardNum));
		return "Board/board_detail";
	}
	

}
