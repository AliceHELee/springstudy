package com.model.web.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.PlatformTransactionManager;

import com.model.web.dto.TicketDto;

public class TicketDao {
	JdbcTemplate template;
	PlatformTransactionManager transactionManager;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void transactionManager(PlatformTransactionManage transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	
	public void buyTicket(final TicketDto dto) {
		System.out.println("buyTicket()");
		System.out.println("dto.getConsumerId() :" + dto.getConsumerId());
		System.out.println("dto.getAmount() :" + dto.getAmount());
		
		TransactionDefinition definition = new defaultTransactionDefinition();
		TransactionStatus status = new transactionManager.getTransaction(definition);
		try {
			//Step1
			String query = "inserto into card (consuerId, amount) values (?,?)");
			template.update(query, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, dto.getConsumerId());
					ps.setString(2, dto.getAmound());
				}
			});
			//Step2
			query = "insert into ticket (consumerId, Countnum) values (?, ?)";
			template.update(query, pss) 
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getConsumerId());
				ps.setString(2, dto.getAmound());
	
			transactionManager.commit(status);
			
		}catch {
			
		}
	
	
}
