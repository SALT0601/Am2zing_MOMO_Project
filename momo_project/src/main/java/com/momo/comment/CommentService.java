package com.momo.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.domain.Board;
import com.momo.domain.Comment;
import com.momo.domain.Member;
import com.momo.member.MemberCustomRepository;


@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	   private MemberCustomRepository memberCustomRepository;


	public List<Comment> findByBoardNum(int boardNum){

		Board board = boardRepository.findById((long) boardNum).get();
		return commentRepository.findByBoard(board);
	}
	
	public void save(String commentContent, int boardNum, String memEmail) {
	      Board board = boardRepository.findById((long) boardNum).get();
	      Member member = memberCustomRepository.findByMemEmail(memEmail);
	      Comment comment = new Comment();
	      comment.setCommentContent(commentContent);
	      comment.setBoard(board);
	      comment.setMember(member);
	      
	      commentRepository.save(comment);
	   }
	

	 public ResponseEntity<?> update(Map<String,String> map) {
	      
	      Map<String, String> tempMap=new HashMap<>();
	   
	      String commentContent = map.get("commentContent");
	      String memEmail = map.get("memEmail");
	
	      int boardNum = Integer.parseInt(map.get("boardNum"));
	      this.save(commentContent, boardNum, memEmail);
	      
	      try {      
	         tempMap.put("commentContent", map.get("commentContent"));          
	      } catch(Exception e) {
	         return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	      }
	      return new ResponseEntity<Map<String, String>>(tempMap, HttpStatus.OK);
	   }
	   

	public void delete(Long commentNum) {
		commentRepository.deleteById((long) commentNum);
	}
	
}
