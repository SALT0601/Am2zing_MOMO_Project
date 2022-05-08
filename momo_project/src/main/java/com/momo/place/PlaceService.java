package com.momo.place;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.momo.board.BoardRepository;

import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberCustomRepository;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberCustomRepository memberCustomRepository;
	
	//Place객체 생성후 set으로 값대입, repository로 DB저장
	public void save(String placeTitle, String placeLat, String placeLng,
			String placeId, String placeContent, String memEmail, int boardNum, String placeImg) {
		Board board = boardRepository.findById((long) boardNum).get();
		Member member = memberCustomRepository.findByMemEmail(memEmail);
		Place place = new Place();
		place.setPlaceTitle(placeTitle);
		place.setPlaceLat(placeLat);
		place.setPlaceLng(placeLng);
		place.setPlaceId(placeId);
		place.setPlaceContent(placeContent);
		place.setMember(member);
		place.setBoard(board);
		place.setPlaceImg(placeImg);

		this.placeRepository.save(place);
	}
			
	
	public void delete(Long placeNum) {
		placeRepository.deleteById(placeNum);
	}
	
	//JSON형식으로 받은 데이터를 맵으로 받아 각 변수에 대입 , save메소드로 db저장
	public ResponseEntity<?> update(Map<String,String> map) {
		Map<String, String> tempMap=new HashMap<>();
		String memEmail = map.get("memEmail");
		String placeTitle = map.get("placeTitle");
		String placeLat = map.get("placeLat");
		String placeLng = map.get("placeLng");
		String placeId = map.get("placeId");
		String placeContent = map.get("placeContent");
		String placeImg = map.get("placeImg");
		int boardNum = Integer.parseInt(map.get("boardNum"));
		this.save(placeTitle, placeLat, placeLng, placeId, placeContent, memEmail, boardNum, placeImg);
		
		try {
			tempMap.put("placeTitle", map.get("placeTitle"));
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		//타이틀이랑 PK를 Map으로 view로 내려보냄
		return new ResponseEntity<Map<String, String>>(tempMap, HttpStatus.OK);
	}
	
	public List<Place> getPlaceList(int boardNum){
		Board board = boardRepository.findById((long) boardNum).get();
		List<Place> placeList = placeRepository.findByBoard(board);
		return placeList;
	}
	
	
	public String upload(MultipartFile multi, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		//파일 저장 경로 설정..
		String path = new File("").getAbsolutePath()+"\\src\\main\\resources\\static\\images\\place\\";
		String url = null;
		try {
			String uploadpath = path;
			//파일의 원래 이름
			String originFileName = multi.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf(".")
					,originFileName.length());
			//뒤에 붙을 이름 생성
			String saveFileName = genSaveFileName(extName);
			
			if(!multi.isEmpty()) {
				File file = new File(uploadpath, multi.getOriginalFilename()+"_"+saveFileName);
				String name = multi.getOriginalFilename()+"_"+saveFileName;
				multi.transferTo(file);
				model.addAttribute("fileName", multi.getOriginalFilename());
				model.addAttribute("uploadPath", "/images/place/"+name);
				return "Board/board_filepath";
			}
		} catch(Exception e){
			
		}
		return "Board/board_filepath";
	}
	
	//이름생성 메소드.. 날짜 시간으로..
	private String genSaveFileName(String extName) {
        String fileName = "";
        
        Calendar calendar = Calendar.getInstance();
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;
        return fileName;
    }
}
