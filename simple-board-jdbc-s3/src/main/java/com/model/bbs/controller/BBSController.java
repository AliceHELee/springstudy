package com.model.bbs.controller;
 
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.bbs.BBSService;
import com.model.bbs.dto;
 
@Controller
public class BBSController {
 
    @Autowired
    private BBSService bbsService;
    
    BBSDto article;
    
    /**
     * 게시판 리스트 출력
     */
    @RequestMapping(value="/list.bbs")
    public String list(Model model, int pageNum) {
        bbsService.list(pageNum, model);
        
        return "list";
    }
    
    /**
     * 글 읽기 화면 및 기능
     */
    @RequestMapping("/content.bbs")        // value값만 줄 땐 value= 생략 가능하다.
    public String content(Model model, String articleNumber, int pageNum) {
        article = bbsService.content(articleNumber);
        model.addAttribute("article", article);
        model.addAttribute("pageNum", pageNum);
        
        return "content";
    }
    
    /**
     * 로그인 기능
     */
    @RequestMapping(value="/login.bbs", method=RequestMethod.POST)
    public String login(HttpSession session, String id, String pw, Model model) {
        return bbsService.login(session, id, pw);
    }
    
    /**
     * 로그아웃 기능
     */
    @RequestMapping(value="/logout.bbs")
    public String logout(HttpSession session) {
        session.invalidate();
        
        return "login";
    }
    
    /**
     * 글 쓰기 화면
     */
    @RequestMapping(value="/write.bbs", method=RequestMethod.GET)        // GET, POST방식으로 구분할 수 있다, writeForm.jsp 변경
    public String writeForm(HttpSession session, Model model, String pageNum) {
        model.addAttribute("pageNum", pageNum);
        
        // Interceptor를 이용하면 더이상 이런 코드를 작성하지 않아도 된다.
        if(session.getAttribute("id") != null)
            return "writeForm";
        else
            return "login";
    }
    
    /**
     * 글 쓰기 기능
     */
    @RequestMapping(value="/write.bbs", method=RequestMethod.POST)
    public String write(HttpSession session, BBSDto article) {        // DTO로 바로 받아올 수 있다.
        article.setId(session.getAttribute("id").toString());
        bbsService.write(article);
        
        return "redirect:/list.bbs?pageNum=1";
    }
 
    /**
     * 답글 쓰기 화면
     */
    @RequestMapping(value="/replyForm.bbs")
    public String replyForm(Model model, String pageNum, String groupId, String depth, String pos) {
        // 게시판 플로우를 이해해야 한다.
        // Model2에서 request.getParameter로 들고 오던 값들을 생각해야 한다.
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("groupId", groupId);
        model.addAttribute("depth", depth);
        model.addAttribute("pos", pos);
        
        return "replyForm";
    }
    
    /**
     * 답글 쓰기 기능
     */
    @RequestMapping(value="/reply.bbs")
    public String reply(HttpSession session, BBSDto article, String pageNum) {    
        article.setId(session.getAttribute("id").toString());
        bbsService.reply(article);
        
        return "redirect:/list.bbs?pageNum=" + pageNum;
    }
    
    /**
     * 글 수정 화면
     */
    @RequestMapping(value="/updateForm.bbs")
    public String updateForm(Model model, String pageNum, String articleNumber) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("article", bbsService.updateForm(articleNumber));
        
        return "updateForm";
    }
    
    /**
     * 글 수정 기능
     */
    @RequestMapping(value="/update.bbs")
    public String update(String pageNum, BBSDto article) {
        bbsService.update(article);
        return "redirect:/list.bbs?pageNum=" + pageNum;
    }
    
    /**
     * 글 삭제 기능
     */
    @RequestMapping(value="/delete.bbs")
    public String delete(String articleNumber, String pageNum) {
        bbsService.delete(articleNumber);
 
        return "redirect:/list.bbs?pageNum=" + pageNum;
    }
    
}
