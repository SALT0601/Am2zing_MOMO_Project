package com.momo.domain;

import java.io.Serializable;

import javax.persistence.*;

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
public class Place implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeNum;

    @Column
    private String placeTitle;

    @Column
    private String placeContent;

    @Column
    private String placeId;
    
    @Column
    private String placeLat; //위도
    
    @Column
    private String placeLng; //경도
    
    @Column
    private String placeImg;


    @ManyToOne
    private Member member;

    @ManyToOne
    private Board board;
   
	}