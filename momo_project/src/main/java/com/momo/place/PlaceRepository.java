package com.momo.place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Board;
import com.momo.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
	List<Place> findByBoard(Board board);
	
}
