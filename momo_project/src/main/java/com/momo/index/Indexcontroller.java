package com.momo.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Indexcontroller {
	
	@GetMapping("/")
	public String index(){
	return "index";
	}

}
