package com.model.bbs.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.bbs.LoginStatus;
import com.model.bbs.dto;
 
@Repository
public class BBSDaoImpl implements BBSDao, LoginStatus {
    
    @Autowired
    private OracleDBConnector orclDbc;
    
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer query;
    BBSDto article;
    ArrayList<BBSDto> articles;
 
    @Override
    public ArrayList<BBSDto> selectArticles(int startRow, int endRow) {
        conn = orclDbc.getConnection();
        query = new StringBuffer();
        articles = new ArrayList<>();
        
        query.append("SELECT bbs.* ");
        query.append("  FROM (SELECT rownum AS row_num, bbs.* ");
        query.append("            FROM (SELECT article_number, id, title, depth, hit, write_date ");
        query.append("                        FROM bbs ");
        query.append("                       ORDER BY group_id DESC, pos ");
        query.append("                    ) bbs ");
        query.append("          ) bbs ");
        query.append(" WHERE row_num BETWEEN ? AND ?");
        
        try {
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();
        
        
            while(rs.next()) {
                article = new BBSDto();
                article.setArticleNumber(rs.getInt("article_number"));
                article.setId(rs.getString("id"));
                article.setTitle(rs.getString("title"));
                article.setDepth(rs.getInt("depth"));
                article.setHit(rs.getInt("hit"));
                article.setWriteDate(rs.getTimestamp("write_date"));
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return articles;
    }
    
    @Override
    public int getArticleTotalCount() {
        conn = orclDbc.getConnection();
        int totalCount = 0;
        
        try {
            pstmt = conn.prepareStatement("SELECT count(*) AS total_count FROM bbs");
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return totalCount;
    }
    
    @Override
    public BBSDto selectArticle(String articleNumber) {
        conn = orclDbc.getConnection();
        query = new StringBuffer();
        query.append("SELECT * FROM bbs WHERE article_number = ?");
        
        try {
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, articleNumber);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                article = new BBSDto();
                article.setArticleNumber(rs.getInt("article_number"));
                article.setId(rs.getString("id"));
                article.setTitle(rs.getString("title"));            
                article.setDepth(rs.getInt("depth"));            
                article.setContent(rs.getString("content"));
                article.setHit(rs.getInt("hit"));
                article.setGroupId(rs.getInt("group_id"));
                article.setPos(rs.getInt("pos"));
                article.setWriteDate(rs.getTimestamp("write_date"));
                article.setFileName(rs.getString("file_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return article;
    }
    
    @Override
    public synchronized int upHit(String articleNumber) {
        conn = orclDbc.getConnection();
        int result = 0;
        
        try {
            pstmt = conn.prepareStatement("UPDATE bbs SET hit = hit + 1 WHERE article_number = ?");
            pstmt.setString(1, articleNumber);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
    
    @Override
    public int loginCheck(String id, String pw) {
        conn = orclDbc.getConnection();
        int result = 0;
        
        try {
            pstmt = conn.prepareStatement("SELECT pw FROM users WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            
            if(rs.next()) {
                if(pw.equals(rs.getString("pw")))
                    // 직관적으로 알 수 있도록 상수로 정의하자.
                    result = LOGIN_SUCCESS;
                else
                    result = PASS_FAIL;
            } else
                result = NOT_MEMBER;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
    
    @Override
    public synchronized int insertArticle(BBSDto article) {
        conn = orclDbc.getConnection();
        int result = 0;
        
        query = new StringBuffer();
        query.append("INSERT INTO bbs ");
        query.append("VALUES(bbs_seq.nextval, ?, ?, ?, bbs_seq.currval, 0, 0, 0, sysdate, ?)");
        
        try {
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, article.getId());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getFileName());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
    
    public synchronized int upPos(int groupId, int pos) {
        conn = orclDbc.getConnection();
        int result = 0;
        query = new StringBuffer();
        query.append("UPDATE bbs");
        query.append("     SET pos = pos + 1");
        query.append(" WHERE group_id = ?");
        query.append("     AND pos > ?");
        
        try {
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, pos);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        disconnect();
        
        return result;
    }
    
    @Override
    public synchronized int replyArticle(BBSDto article) {
        this.upPos(article.getGroupId(), article.getPos());
        conn = orclDbc.getConnection();
        int result = 0;
        query = new StringBuffer();
        query.append("INSERT INTO bbs ");
        query.append("VALUES(bbs_seq.nextval, ?, ?, ?, ?, ?, ?, 0, sysdate, ?)");
        try {
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, article.getId());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setInt(4, article.getGroupId());
            pstmt.setInt(5, article.getDepth() + 1);
            pstmt.setInt(6, article.getPos() + 1);
            pstmt.setString(7, article.getFileName());
 
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
 
    @Override
    public synchronized int updateArticle(BBSDto article) {
        conn = orclDbc.getConnection();
        int result = 0;
        
        try {
            pstmt = conn.prepareStatement("UPDATE bbs SET title=?, content=? WHERE article_number=?");
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getArticleNumber());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
    
    @Override
    public synchronized int deleteArticle(String articleNumber) {
        conn = orclDbc.getConnection();
        int result = 0;
        
        try {
            pstmt = conn.prepareStatement("DELETE FROM bbs WHERE article_number = ?");
            pstmt.setString(1, articleNumber);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        disconnect();
        
        return result;
    }
    
    public void disconnect() {
        try {
            if(rs != null) {
                rs.close();
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}