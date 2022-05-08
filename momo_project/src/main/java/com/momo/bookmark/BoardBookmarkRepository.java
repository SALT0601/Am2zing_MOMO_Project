package com.momo.bookmark;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
import com.momo.domain.Member;

public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, Long>{
	BoardBookmark findByMemberAndBoard(Member member, Board board);

	List<BoardBookmark> findByMember(Member member, Pageable pageable);
	
	List<BoardBookmark> findByBoard(Board board);
	
	List<BoardBookmark> findByMember(Member member);
}
