package com.model.s08;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AdminConnection {
	//private Environment env;
	private String adminId;
	private String adminPw;
	private String sub_adminId;
	private String sub_adminPw;
	
	public String getAdminId() {return adminId;	}
	public void setAdminId(String adminId) {this.adminId = adminId;	}
	public String getAdminPw() {return adminPw;	}
	public void setAdminPw(String adminPw) {this.adminPw = adminPw;	}
	
	public String getSubAdminId() {return sub_adminId;	}
	public void setSubAdminId(String sub_adminId) {this.sub_adminId = sub_adminId;	}
	public String getSubAdminPw() {return sub_adminPw;	}
	public void setSubAdminPw(String sub_adminPw) {this.sub_adminPw = sub_adminPw;	}

/*	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()");
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
	}*/
	 
}
