package com.ae.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ae.poc.entity.PocDetails;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
   

    public void sendPocMail(PocDetails poc, String... recipients) {
        String subject = "New POC Created - ID: " + poc.getPocId();
        
        String body = "A new POC has been created:\n\n" +
                "POC ID: " + poc.getPocId() + "\n" +
                "Sales Person: " + poc.getSpName() + "\n" +
                "Region: " + poc.getRegion() + "\n" +
                "End Customer Type: " + poc.getEndCustomerType() + "\n" +
                "Process Type: " + poc.getProcessType() + "\n" +
                "Customer Company: " + poc.getCompanyName() + "\n" +
                "Customer SPOC: " + poc.getSpoc() + "\n" +
                "Customer SPOC Manager: " + poc.getSpocEmail() + "\n" +
                "Customer Designation: " + poc.getDesignation() + "\n" +
                "Customer Mobile: " + poc.getMobileNumber() + "\n" +
                "Use Case: " + poc.getUsecase() + "\n" +
                "Brief: " + poc.getBrief() + "\n\n" +

                // ✅ Partner section (only if endCustomerType is Partner)
                (poc.getEndCustomerType() != null && ((String) poc.getEndCustomerType()).equalsIgnoreCase("Partner")
                        ? "Partner Company: " + poc.getPartnerCompanyName() + "\n" +
                          "Partner SPOC: " + poc.getPartnerSpoc() + "\n" +
                          "Partner SPOC Manager: " + poc.getPartnerSpocEmail() + "\n" +
                          "Partner Designation: " + poc.getPartnerDesignation() + "\n" +
                          "Partner Mobile: " + poc.getPartnerMobileNumber() + "\n\n"
                        : "") +

                "Created successfully! 🚀";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients); // multiple emails
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}

