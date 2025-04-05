package com.Turtles.Time_off_Manager_BackEnd;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class Mycontroller {
    @GetMapping("/hello")
    public String sayHello(){
         return "Greetings from Spring Boot!";
    }
    /*@RequestMapping("/resource")
    public Map<String,Object> home(){ 
        Map<String,Object> model=new HashMap<String,Object>();
        model.put("id",UUID.randomUUID().toString());
        model.put("content","Hello World");
        return model;
    }*/

}
