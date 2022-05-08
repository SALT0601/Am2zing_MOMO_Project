package com.momo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Comment;

public interface AdminCommentRepository extends JpaRepository<Comment, Long>{

}
