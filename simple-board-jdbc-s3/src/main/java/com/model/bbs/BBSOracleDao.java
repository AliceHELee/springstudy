package com.model.bbs;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * BBSOracleDao.java
 * DB를 사용해 데이터를 조회하거나 조작하는 기능을 구현한 클래스
 * 일반, 공지게시판 등 재사용성을 늘리기 위해 클래스로 분리한다.
 */
public class BBSOracleDao implements LoginStatus {
  private static BBSOracleDao bbsOracleDao;
  private OracleDBConnector orclDbc = OracleDBConnector.getInstacne();
   
  Connection conn;
  PreparedStatement pstmt;
  ResultSet rs;
  StringBuffer query;
   
  BBSDto article;
  ArrayList<BBSDto> articles;
 
  private BBSOracleDao() {}
   
  public static BBSOracleDao getInstance() {
    if(bbsOracleDao == null) {
      bbsOracleDao = new BBSOracleDao();
    }
    return bbsOracleDao;
  }
   
  // synchronized, 한 명의 글쓰기를 처리한 후 다른 사람의 글쓰기를 처리해야한다.
  public synchronized int insertArticle(BBSDto article) throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    query = new StringBuffer();
    query.append("INSERT INTO bbs ");
    query.append("VALUES(bbs_seq.nextval, ?, ?, ?, bbs_seq.currval, 0, 0, 0, sysdate, ?)");
    pstmt = conn.prepareStatement(query.toString());
    // parameterIndex는 쿼리문의 ? 순서대로 적어주며, 1부터 시작한다.
    pstmt.setString(1, article.getId());
    pstmt.setString(2, article.getTitle());
    pstmt.setString(3, article.getContent());
    pstmt.setString(4, article.getFileName());
     
    int result = pstmt.executeUpdate();
     
    disconnect();
     
    return result;
  }
   
  public BBSDto selectArticle(String articleNumber) throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    query = new StringBuffer();
    query.append("SELECT * FROM bbs WHERE article_number = ?");
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
     
    disconnect();
     
    return article;
  }
   
  public ArrayList<BBSDto> selectArticles(int startRow, int endRow) throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    query = new StringBuffer();
    // BETWEEN ? AND ? 일 때는 Inline View 두 번 해야한다.
    // 만약 BETWEEN 1 AND ? 이라면 인라인뷰 한 번만으로 가능하다.
    query.append("SELECT bbs.* ");
    query.append("  FROM (SELECT rownum AS row_num, bbs.* ");
    query.append("        FROM (SELECT article_number, id, title, depth, hit, write_date ");
    query.append("                FROM bbs ");
    query.append("               ORDER BY group_id DESC, pos ");
    query.append("            ) bbs ");
    query.append("      ) bbs ");
    query.append(" WHERE row_num BETWEEN ? AND ?");
    pstmt = conn.prepareStatement(query.toString());
    pstmt.setInt(1, startRow);
    pstmt.setInt(2, endRow);
    rs = pstmt.executeQuery();
     
    articles = new ArrayList<>();
     
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
     
    disconnect();
     
    return articles;
  }
   
  public int getArticleTotalCount() throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    pstmt = conn.prepareStatement("SELECT count(*) AS total_count FROM bbs");
    rs = pstmt.executeQuery();
     
    int totalCount = 0;
     
    if(rs.next()) {
      totalCount = rs.getInt(1);    // index로 받아오는 것이 속도면에선 좋다.
  //    totalCount = rs.getInt("total_count");    // 하지만 컬럼명이 보기엔 명확한 듯 하다.
    }
     
    disconnect();
     
    return totalCount;
  }
   
  public synchronized int upHit(String articleNumber) throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    pstmt = conn.prepareStatement("UPDATE bbs SET hit = hit + 1 WHERE article_number = ?");
    pstmt.setString(1, articleNumber);
    int result = pstmt.executeUpdate();
     
    disconnect();
     
    return result;
  }
 
  /**
   * BBSOracleDao.java에 추가한다.
   * @param id
   * @param pw
   * @return int
   * 대한민국
   */
  public int loginCheck(String id, String pw) throws ClassNotFoundException, SQLException {
    conn = orclDbc.getConnection();
    pstmt = conn.prepareStatement("SELECT pw FROM users WHERE id = ?");
    pstmt.setString(1, id);
    rs = pstmt.executeQuery();
     
    int result = 0;
     
    if(rs.next()) {
      if(pw.equals(rs.getString("pw")))
        // 직관적으로 알 수 있도록 상수로 정의하자.
        result = LOGIN_SUCCESS;
      else
        result = PASS_FAIL;
    } else
      result = NOT_MEMBER;
     
    disconnect();
     
    return result;
  }
   
  public void disconnect() throws SQLException {
    if(rs != null) {
      rs.close();
    }
    pstmt.close();
    conn.close();
  }
   
}
