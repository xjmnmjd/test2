package com.sojson.common.timer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时任务恢复数据
 *
 */
@Component
public class ToTimer{	
	@Scheduled(cron = "0 */1 * * * ?")
	public void run(){
		/**
		 * 调用存储过程，重新创建表，插入初始化数据。
		 */
		//roleService.initData();

	}

	
	
	
	
	
	
}
