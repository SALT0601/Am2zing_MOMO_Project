package com.momo.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.domain.Member;
import com.momo.member.PrincipalDetails;

@Controller
public class BookmarkController {
	
	@Autowired
	BoardBookMarkService boardBookMarkService;
	
	@PostMapping("/boardBookmark")
	@ResponseBody
	public String boardBookmark(@RequestParam int boardNum, @AuthenticationPrincipal PrincipalDetails principal) {
		if(principal == null) {
        	return "/member/login";
        } else {
        	Member member = principal.getMember();
        	boardBookMarkService.save(member, boardNum);
        }
		return "/post/"+boardNum;
	}
	
	@PostMapping("/unBoardBookmark")
	@ResponseBody
	public String unBoardBookmark(@RequestParam int boardNum, @AuthenticationPrincipal PrincipalDetails principal) {
        Member member = principal.getMember();
        boardBookMarkService.deleteBoardBookMark(member, boardNum);
        
        return "/post/"+boardNum;
	}
}
