package com.mail.ctl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.bean.Employee;

@RestController
public class EmployeeController {

	@Autowired
	JavaMailSender javaMailSender;
	
	@PostMapping("/sendMail")
	public ResponseEntity<Object> sendEmail(@RequestBody Employee employee){
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("oldisgold10dob@gmail.com");
		sm.setTo("huma.triceria@gmail.com");
		sm.setSubject("First");
		sm.setText("Hi/....");
		javaMailSender.send(sm);
		return generateResponse("Email sent to", HttpStatus.OK, employee);
	}
	
	public ResponseEntity<Object> generateResponse(String msg, HttpStatus st, Object respone){
		Map<String, Object> map = new 
				HashMap<String, Object>();
		map.put("message", msg);
		map.put("status", st);
		map.put("data", respone);
		return new ResponseEntity<Object>(map,st);
		
	}
}
