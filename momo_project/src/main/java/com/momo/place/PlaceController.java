package com.momo.place;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.momo.domain.Place;


@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	
	//JSON으로 보낸 Place정보를 map으로 받음 
	@PostMapping("/placeList/{boardNum}")
	public String placeList(@PathVariable("boardNum") int boardNum, @RequestBody Map<String,String> map, Model model) {
		placeService.update(map);
		List<Place> placeList = placeService.getPlaceList(boardNum);
		model.addAttribute("places", placeList);
		return "Place/place_list";
	}
	
	@DeleteMapping("/deletePlace/{placeNum}")
	public String delete(@PathVariable("placeNum") int placeNum, @RequestParam int boardNum, Model model) {
		placeService.delete((long) placeNum);
		List<Place> placeList = placeService.getPlaceList(boardNum);
		model.addAttribute("places", placeList);
		return "Place/place_list";
	}
	
	 @PostMapping("/upload")
	 public String upload(@RequestParam("file1") MultipartFile multi, HttpServletRequest request, 
			 HttpServletResponse response, Model model) {
		 return placeService.upload(multi, request, response, model);
	 }
}
