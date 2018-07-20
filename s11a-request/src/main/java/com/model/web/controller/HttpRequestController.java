package com.model.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HttpRequestController {

	// STEP 1
	@RequestMapping("/board/confirm_id")
	public String confirmId(HttpServletRequest httpServletRequest, 
		Model model) {
		//   /board/confirm_id?id=pppp&pw=12345
		String id = httpServletRequest.getParameter("id");	
		String pw = httpServletRequest.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);

		return "board/confirm_id";
	}


	// STEP 2
	@RequestMapping("/board/check_id")
	public String checkId(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/check_id";
	}
	
	
	// SETP 3 
	@RequestMapping("/member/join")
	public String joinData(
		@RequestParam("id") String id,
		@RequestParam("pw") String pw,
		@RequestParam("name") String name,
		@RequestParam("email") String email,
		Model model) {
		
		Member member = new Member();
		
		member.setId(id);
		member.setName(name);
		member.setPw(pw);
		member.setEmail(email);

		model.addAttribute("member", member);
		
		return "member/join";
	}

	// SETP 4
	@RequestMapping("/member/joinsimple")
	public String joinDataSimple(@ModelAttribute("mem") Member member) {
		return "member/join";
	}
	
	@RequestMapping("/member/{memberId}")
	public String getMemberId(Member member) {
		return "member/join";
	}
	
	
	
}