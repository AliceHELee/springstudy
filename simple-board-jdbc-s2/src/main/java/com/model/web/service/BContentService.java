package com.model.web.service;

import com.model.web.dao.BDao;
import com.model.web.dto.BDto;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public class BContentService implements BService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		model.addAttribute("content_view", dto);
	}
}