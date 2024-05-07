package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestSchedule {

	@Value("${fixedRate}")
	private Integer fixedRate;
	
	private RestTemplate restTemplate = new RestTemplate();



//	@Scheduled(fixedDelay = 3 * 1000)
//	@Scheduled(cron = "0 32 9 * * ?")
	public void cronTask() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println("固定時間 " + LocalDateTime.now().format(formatter) + " 執行排程");
	}

//	@Scheduled(fixedRateString = "${fixedRate}") // 如果要用properties參數帶入
//	@Scheduled(fixedRate = 3000)				 // 不管前次是否執行完，只要執行後經過特定時間就執行下次
//	@Scheduled(fixedDelay = 3000)			 	 // 執行後經固定時間再執行
//	@Scheduled(initialDelay = 3000)				 // 程式啟動後經過指定時間後執行一次
	public void fixedTask() {
		System.out.println("每" + fixedRate / 1000 + "秒執行排程");
	}
	
	
//	@Scheduled(fixedRate = 3000)
	public void sendMessageToQueue() {
		String urlForPost = "http://localhost:8080/send";

        int numberOfThreads = 1;

        for (int i = 0; i < numberOfThreads; i++) {
    		String response = restTemplate.postForObject(urlForPost, "Hello" ,String.class);
//    		String response = restTemplate.getForObject(urlForPost, String.class);
            System.out.println(response);
        }
		
	}
}
