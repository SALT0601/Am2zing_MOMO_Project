package com.momo.comment;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.momo.domain.Comment;



@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	

	
	@PostMapping("/commentList/{boardNum}")
	public String commentList(@PathVariable("boardNum") int boardNum, @RequestBody Map<String,String> map,Model model) {
		commentService.update(map);
		List<Comment> commentList = commentService.findByBoardNum(boardNum);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("comments", commentList);
		return "comment/comment_commentList";
	}

	@PostMapping("/commentList02/{boardNum}")
	public String commentList02(@PathVariable("boardNum") int boardNum, Model model) {
		List<Comment> commentList = commentService.findByBoardNum(boardNum);
		model.addAttribute("comments", commentList);
		return "comment/comment_commentList";
	}

	 
	 @DeleteMapping("/deleteComment/{commentNum}")
	    public String delete(@PathVariable("commentNum") long commentNum, @RequestParam int boardNum,Model model) {
	       commentService.delete(commentNum);
	       List<Comment> comments = commentService.findByBoardNum(boardNum);
	       model.addAttribute("comments", comments);
	       return "comment/comment_commentList";
	    }
}


