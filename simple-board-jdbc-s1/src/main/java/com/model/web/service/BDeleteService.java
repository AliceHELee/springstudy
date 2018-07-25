package com.model.web.service;
import com.model.web.dao.BDao;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public class BDeleteService implements BService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest httpServletRequest = (HttpServletRequest) map.get("request");
		
		String bId = httpServletRequest.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}
}