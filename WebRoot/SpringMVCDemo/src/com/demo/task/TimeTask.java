package com.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.service.UserService;

/**
 * 定时器
 * 
 * @date 2017-10-10
 * @author qchen
 */
@Component
@PropertySource("classpath:config.properties")
public class TimeTask {
	@Autowired
	private UserService userService;
	@Value("#{config['logOverDays']}")
	private String logOverDays;
	@Scheduled(cron = "0/1 * * * * ?")
	private void tranferLogToHistory() {
		this.userService.tranferLogToHistory(logOverDays);
	}
}
