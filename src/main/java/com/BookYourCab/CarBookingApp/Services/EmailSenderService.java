package com.BookYourCab.CarBookingApp.Services;

public interface EmailSenderService {
    public void sendEmail(String toEmail,String subject,String body);
}
