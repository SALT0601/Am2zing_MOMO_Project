package com.momo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Board;

public interface AdminBoardRepository extends JpaRepository<Board, Long> {

}
