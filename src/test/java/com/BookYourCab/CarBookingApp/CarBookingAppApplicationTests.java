package com.BookYourCab.CarBookingApp;

import com.BookYourCab.CarBookingApp.Services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarBookingAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("arjoon2429@gmail.com",
				"Spring Boot Mailer",
				"This is my body");
	}

}
