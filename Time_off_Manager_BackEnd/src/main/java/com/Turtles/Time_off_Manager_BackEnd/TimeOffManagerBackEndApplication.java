package com.Turtles.Time_off_Manager_BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeOffManagerBackEndApplication {
	// @RequestMapping("/resoure")
	// public Map<String,Object> home(){
	// 	Map<String,Object> model=new HashMap<>();
	// 	model.put("id",UUID.randomUUID().toString());
	// 	model.put("content","Hello World");
	// 	return model;
	// }

	public static void main(String[] args) {
		SpringApplication.run(TimeOffManagerBackEndApplication.class, args);
	}

}
