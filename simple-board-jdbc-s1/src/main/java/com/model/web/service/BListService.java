package com.model.web.service;

import com.model.web.dao.BDao;
import com.model.web.dto.BDto;

import java.util.ArrayList;
import org.springframework.ui.Model;

public class BListService implements BService {

	@Override
	public void execute(Model model) {

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		System.out.println(dtos.size());               //arrayList 갯수 체크용
		model.addAttribute("list", dtos);
	}
}