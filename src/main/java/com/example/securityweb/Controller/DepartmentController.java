package com.example.securityweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DepartmentController {

	@GetMapping("/crushing")
	public String Crushing() {
		return "crushing";
	}
	
	@GetMapping("/caneyard")
	public String Caneyard() {
		return "caneyard";
	}
	
	@GetMapping("/electrical")
	public String Electrical() {
		return "electricaldept";
	}
}
