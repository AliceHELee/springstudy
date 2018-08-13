package com.model.bbs.dao;
 
import java.util.ArrayList;

import com.model.bbs.dto;
 
public interface BBSDao {
    
    public ArrayList<BBSDto> selectArticles(int startRow, int endRow);
    
    public int getArticleTotalCount();
    
    public BBSDto selectArticle(String articleNumber);
    
    public int upHit(String articleNumber);
    
    public int loginCheck(String id, String pw);
    
    public int insertArticle(BBSDto article);
    
    public int replyArticle(BBSDto article);
    
    public int updateArticle(BBSDto article);
    
    public int deleteArticle(String articleNumber);
    
} 
 