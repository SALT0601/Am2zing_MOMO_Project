package com.momo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BoardBookmark {
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardBookmarkNum;
	
	@ManyToOne
	private Member member;
	
	@ManyToOne
	private Board board;
}
