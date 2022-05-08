package com.momo.bookmark;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.common.util.pagination.Paging;
import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
import com.momo.domain.Member;

@Service
public class BoardBookMarkService {
	@Autowired
	private BoardBookmarkRepository boardBookmarkRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public boolean isBoardBookmarkChecked(Member member, long boardNum) {
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		if(boardBookmarkRepository.findByMemberAndBoard(member, board) == null) {
			return true;
		} else return false;
	}
	
public Map<String,Object> findAllByMember(Member member, int page){
		
		int cntPerPage = 6;
		List<BoardBookmark> boardBookmarks = boardBookmarkRepository
				.findByMember(member, PageRequest.of(page-1, cntPerPage, Direction.DESC, "boardBookmarkNum"));
		
		Paging paging = Paging.builder()
				.url("/member/boardBookmark")
				.total((int)boardBookmarkRepository.count())
				.cntPerPage(cntPerPage)
				.curPage(page)
				.blockCnt(10)
				.build();
		
		return Map.of("boardBookmarks",boardBookmarks,"paging",paging);
	}
	
	public void save(Member member, long boardNum) {
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		BoardBookmark boardBookmark = new BoardBookmark();
		boardBookmark.setMember(member);
		boardBookmark.setBoard(board);
		
		boardBookmarkRepository.save(boardBookmark);
	}
	
	public void deleteBoardBookMark(Member member, long boardNum) {
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		long boardBookmarkNum = boardBookmarkRepository.findByMemberAndBoard(member, board).getBoardBookmarkNum();
		
		boardBookmarkRepository.deleteById(boardBookmarkNum);
	}
}
