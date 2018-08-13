package com.model.bbs;
 
import java.util.ArrayList;
 
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.model.bbs.dto;
import com.model.bbs.common.Page;
import com.model.bbs.dao.BBSDao;
 
@Service
public class BBSServiceImpl implements BBSService {
    
    @Autowired
    private BBSDao bbsDao;
    
    @Autowired
    private Page page;
    
    @Override
    public Model list(int pageNum, Model model) {
        ArrayList<BBSDto> articles = null;
 
        int totalCount = 0;
        int pageSize = 10;
        int pageBlock = 10;
        
        totalCount = bbsDao.getArticleTotalCount();
        page.paging(pageNum, totalCount, pageSize, pageBlock);
        articles = bbsDao.selectArticles(page.getStartRow(), page.getEndRow());
        
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("articles", articles);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageCode", page.getSb().toString());
        
        return model;
    }
    
    @Override
    public BBSDto content(String articleNumber) {
        BBSDto article = bbsDao.selectArticle(articleNumber);
        bbsDao.upHit(articleNumber);
        
        return article;
    }
    
    @Override
    public String login(HttpSession session, String id, String pw) {
        String view = null;
        int result = bbsDao.loginCheck(id, pw);
        
        if(result == LoginStatus.LOGIN_SUCCESS) {
            session.setAttribute("id", id);
            view = "redirect:/list.bbs?pageNum=1";
        } else if(result == LoginStatus.PASS_FAIL) {
            view = "login";        // 추후 변경
        } else if(result == LoginStatus.NOT_MEMBER) {
            view = "login";        // 추후 변경
        }
            
        return view;
    }
 
    @Override
    public int write(BBSDto article) {
        return bbsDao.insertArticle(article);
    }
 
    @Override
    public int reply(BBSDto article) {
        return bbsDao.replyArticle(article);
    }
 
    @Override
    public BBSDto updateForm(String articleNumber) {
        return bbsDao.selectArticle(articleNumber);
    }
 
    @Override
    public int update(BBSDto article) {
        return bbsDao.updateArticle(article);
    }
 
    @Override
    public int delete(String articleNumber) {
        return bbsDao.deleteArticle(articleNumber);
    }
    
}
 