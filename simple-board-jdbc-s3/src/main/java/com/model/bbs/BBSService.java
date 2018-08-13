package com.model.bbs;
 
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

//import com.model.bbs.dto;
 
public interface BBSService {
    
    public Model list(int pageNum, Model model);
    
    public BBSDto content(String articleNumber);
    
    public String login(HttpSession session, String id, String pw);
    
    public int write(BBSDto article);
    
    public int reply(BBSDto article);
    
    public BBSDto updateForm(String articleNumber);
    
    public int update(BBSDto article);
    
    public int delete(String articleNumber);
    
}

 