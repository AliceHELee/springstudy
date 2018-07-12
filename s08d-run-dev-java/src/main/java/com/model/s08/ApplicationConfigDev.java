package com.model.s08;

import org.springframework.context.annotation.*;
@Configuration
@Profile("dev")
public class ApplicationConfigDev {
	@Bean
	public ServerInfo serverInfo() {
		ServerInfo info = new ServerInfo();
		info.setIpNum("localhost");
		info.setPortNum("8181");		
		return info;
	}

}
