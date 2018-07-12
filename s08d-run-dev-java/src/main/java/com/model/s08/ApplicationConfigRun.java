package com.model.s08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class ApplicationConfigRun {
@Configuration
@Profile("run")
public class ApplicationConfigDev {
	@Bean
	public ServerInfo serverInfo() {
		ServerInfo info = new ServerInfo();
		info.setIpNum("61.41.116.165");
		info.setPortNum("80");		
		return info;
	}

}
