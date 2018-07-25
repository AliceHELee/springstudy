package com.model.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.model.web.dto.BDto;
import com.model.web.util.Constant;



public class BDao {
	
	//DataSource dataSource;
	
	JdbcTemplate template= null;
	
	public BDao() {
		template = Constant.template;
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		//
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = null;

		String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent "
				+ "from mvc_board order by bGroup desc, bStep asc";
		// query 여러줄일때는 배열로 받음
		dtos = (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class)); //형변환 확인
		return dtos;		
		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		BDto dto;
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		try {
//			connection = dataSource.getConnection();
//			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent "
//					+ "from mvc_board order by bGroup desc, bStep asc";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()) {
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				
//				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//				dtos.add(dto);
//				
//			}
//		} catch (SQLException e) { e.printStackTrace(); } 
//		finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) { e2.printStackTrace(); }
//		}
//		return dtos;
	}

	public BDto contentView(String strID) {
		
		upHit(strID);
		//String query = "select * from mvc_board where bId = " + strID;
		String query = "select * from mvc_board where bId = ? "; // new Object[] 배열처리로 {strID, aa} aa추가 argument 갯수제한처리없이 처리가능 
		return template.queryForObject(query, new Object[] {strID}, new BeanPropertyRowMapper<BDto>(BDto.class)); //하나의 객체만 받아처리. 여러줄list는 배열
		
//		BDto dto = null;
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		try {
//			
//			connection = dataSource.getConnection();
//			String query = "select * from mvc_board where bId = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, Integer.parseInt(strID));
//			resultSet = preparedStatement.executeQuery();
//			
//			if(resultSet.next()) {
//				/*
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				*/
//
//				// 김수정님 변경코드
//				dto = new BDto(resultSet.getInt("bId"),
//						resultSet.getString("bName"), 
//						resultSet.getString("bTitle"), 
//						resultSet.getString("bContent"), 
//						resultSet.getTimestamp("bDate"), 
//						resultSet.getInt("bHit"), 
//						resultSet.getInt("bGroup"), 
//						resultSet.getInt("bStep"),
//						resultSet.getInt("bIndent"));
//			}			
//		} catch(Exception e) { e.printStackTrace(); } 
//		finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch(Exception e2) { e2.printStackTrace(); }
//		}
//		return dto;
	}
	
	public void upHit(final String bId) {
		//Connection connection = null;
		//PreparedStatement preparedStatement = null;
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, Integer.parseInt(bId));		//int와 string 둘다 문제는 안됨	
			}
		});
	}
//		try {
//			connection = dataSource.getConnection();
//			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
//			preparedStatement = connection.prepareStatement(query);
//			//preparedStatement.setString(1, bId);
//			preparedStatement.setInt(1, Integer.parseInt(bId));		//int와 string 둘다 문제는 안됨	
//			int rn = preparedStatement.executeUpdate();		// rn: 디버깅용
//			
//		} catch (Exception e) { e.printStackTrace(); } 
//		finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e) { e.printStackTrace(); }
//		}
//	}

	public void write(final String bName, final String bTitle, final String bContent) {  // final 상수처리
		
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, "
				+ "bGroup, bStep, bIndent) "
				+ "values (mvc_board_seq.nextval, ?, ?, ?, "
				+ "0, mvc_board_seq.currval, 0, 0)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);				
			}
		});
	}	
	
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//		try {
//			
//			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
//					+ "values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, bName);		// ? 1st
//			preparedStatement.setString(2, bTitle);		// ? 2nd
//			preparedStatement.setString(3, bContent);	// ? 3rd
//			int rn = preparedStatement.executeUpdate();
//			
//		} catch (Exception e) { e.printStackTrace(); } 
//		finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();				
//			} catch (Exception e2) { e2.printStackTrace(); }
//		}
//	}

	public void delete(final String bId) {
		
		String query = "delete from mvc_board where bId = ?";		
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bId);
			}
		});	
	}  
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		try {
//			connection = dataSource.getConnection();
//			String query = "delete from mvc_board where bId = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, Integer.parseInt(bId));
//			int rn = preparedStatement.executeUpdate();
//			
//		} catch (Exception e) { e.printStackTrace(); }
//		finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) { e2.printStackTrace(); }
//		}
//	}

	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		
		String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));				
			}
		});
	}
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//		try {
//			connection = dataSource.getConnection();
//
//			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, bName);
//			preparedStatement.setString(2, bTitle);
//			preparedStatement.setString(3, bContent);
//			preparedStatement.setInt(4, Integer.parseInt(bId));
//			int rn = preparedStatement.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}

	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
		
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, Integer.parseInt(bGroup));
			ps.setInt(5, Integer.parseInt(bStep) + 1);
			ps.setInt(6, Integer.parseInt(bIndent) + 1);
			}
		});
	}		
		
//		replyShape(bGroup, bStep);
//		preparedStatement = connection.prepareStatement(query);
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//			connection = dataSource.getConnection();
//			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
//			preparedStatement = connection.prepareStatement(query);
//
//			preparedStatement.setString(1, bName);
//			preparedStatement.setString(2, bTitle);
//			preparedStatement.setString(3, bContent);
//			preparedStatement.setInt(4, Integer.parseInt(bGroup));
//			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
//			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
//
//			int rn = preparedStatement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
	
	
	
	public BDto reply_view(String str) {
		BDto dto = null;
		String query = "select * from mvc_board where bId = " + str; 
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class)); //queryForObject
		//return dto;
	}
//						
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;	 
//		
//		try {
//
//			connection = dataSource.getConnection();
//			String query = "select * from mvc_board where bId = ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, Integer.parseInt(str));
//			resultSet = preparedStatement.executeQuery();
//
//			if(resultSet.next()) {
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//
//				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//
//		return dto;
//	}

	private void replyShape(final String strGroup, final String strStep) {
		
		String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter() {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
			ps.setString(1, strGroup);
			ps.setString(2, strStep);
			}
		});
	}	

//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//			connection = dataSource.getConnection();
//			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, Integer.parseInt(strGroup));
//			preparedStatement.setInt(2, Integer.parseInt(strStep));
//
//			int rn = preparedStatement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}

}
