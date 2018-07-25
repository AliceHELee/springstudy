package com.model.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.web.dao.BDao;
import com.model.web.dto.BDto;


@Controller
public class BController {
		
	//BService service = null;
	//private JdbcTemplate template;

	
/*	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}*/
	
	BDao dao;
	
	@Autowired
	public void setDao(BDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/list")					//web.xml이 하던일
	public String list(Model model) { 
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
//		System.out.println("/list");			//확인
//		service = new BListService();
//		service.execute(model);		
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) { 			 
		//model.addAttribute("request", request);
//		System.out.println("/content_view");	//확인
//		service = new BContentService();
//		service.execute(model);
		BDto dto = dao.contentView(request.getParameter("bId"));
		model.addAttribute("content_view", dto);
		return "content_view";
	
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) { 
		System.out.println("/write_view");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) { 
		//model.addAttribute("request", request);
//		System.out.println("/write");
//		service = new BWriteService();		// 객체 만들고 service에 담아
//		service.execute(model);				// execute실행
		dao.write(request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		return "redirect:list";		//redirect로 호출하는 이유 확인!
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) { 
		//model.addAttribute("request", request);
//		System.out.println("/delete");
//		service = new BDeleteService();
//		service.execute(model);
		dao.delete(request.getParameter("bId")); 
		return "redirect:list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) { 
		//model.addAttribute("request", request);
//		System.out.println("/modify");
//		service = new BModifyService();
//		service.execute(model);
		dao.modify(request.getParameter("bId"), request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent")); 
		return "redirect:list";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) { 
		model.addAttribute("request", request);
		System.out.println("/reply");
//		service = new BReplyService();
//		service.execute(model);
		dao.reply(request.getParameter("bId"), 
				request.getParameter("bName"), 
				request.getParameter("bTitle"), 
				request.getParameter("bContent"), 
				request.getParameter("bGroup"), 
				request.getParameter("bStep"), 
				request.getParameter("bIndent"));		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){ 
		model.addAttribute("request", request);
// 		System.out.println("reply_view()");
//		service = new BReplyViewService();
//		service.execute(model);
		BDto dto = dao.reply_view(request.getParameter("bId"));
		model.addAttribute("reply_view", dto);
		return "reply_view";
	}
	
}