package com.ae.poc.service;


import com.ae.poc.entity.PocDetails;
import com.ae.poc.repo.PocDetailsRepo;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PocDetailsRepo repo;

    public void sendPocMail(Integer pocId, String... recipients) {
        Optional<PocDetails> optionalPoc = repo.findById(pocId);

        if (optionalPoc.isEmpty()) {
            System.err.println("⚠️ No POC found with ID " + pocId);
            return;
        }

        PocDetails poc = optionalPoc.get();

        String subject = "New POC Created - ID: " + poc.getId();

        // ✅ HTML body with professional CSS
        String body =
                "<html>" +
                "<head>" +
                "  <style>" +
                "    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f4f6f9; padding: 20px; }" +
                "    h2 { color: #333; }" +
                "    table { width: 80%; margin: 20px auto; border-collapse: collapse;" +
                "            background: #fff; border-radius: 10px; overflow: hidden;" +
                "            box-shadow: 0 4px 10px rgba(0,0,0,0.1); }" +
                "    th, td { padding: 12px 18px; text-align: left; border-bottom: 1px solid #eee; }" +
                "    th { width: 30%; background: #f1f5ff; color: #333; text-transform: uppercase;" +
                "         letter-spacing: 0.05em; font-weight: 600; }" +
                "    td { width: 70%; color: #444; }" +
                "    tr:nth-child(even) { background: #fafafa; }" +
                "    tr:hover { background: #f1f5ff; }" +
                "    .success { margin: 20px auto; width: 80%; font-size: 16px; font-weight: 600; color: green; }" +
                "  </style>" +
                "</head>" +
                "<body>" +
                "<h2>📢 A new POC has been created</h2>" +
                "<table>" +
                "<tr><th>ID</th><td>" + poc.getId() + "</td></tr>" +
                "<tr><th>Sales Person</th><td>" + safe(poc.getSpName()) + "</td></tr>" +
                "<tr><th>Region</th><td>" + safe(poc.getRegion()) + "</td></tr>" +
                "<tr><th>End Customer Type</th><td>" + safe(poc.getEndCustomerType()) + "</td></tr>" +
                "<tr><th>Process Type</th><td>" + safe(poc.getProcessType()) + "</td></tr>" +
                "<tr><th>Customer Company</th><td>" + safe(poc.getCompanyName()) + "</td></tr>" +
                "<tr><th>Customer SPOC</th><td>" + safe(poc.getSpoc()) + "</td></tr>" +
                "<tr><th>Customer SPOC Email</th><td>" + safe(poc.getSpocEmail()) + "</td></tr>" +
                "<tr><th>Customer Designation</th><td>" + safe(poc.getDesignation()) + "</td></tr>" +
                "<tr><th>Customer Mobile</th><td>" + safe(poc.getMobileNumber()) + "</td></tr>" +
                "<tr><th>Use Case</th><td>" + safe(poc.getUsecase()) + "</td></tr>" +
                "<tr><th>Brief</th><td>" + safe(poc.getBrief()) + "</td></tr>";

        // ✅ Partner Info if Partner
        if ("Partner".equalsIgnoreCase(safe(poc.getEndCustomerType()))) {
            body +=
                "<tr><th>Partner Company</th><td>" + safe(poc.getPartnerCompanyName()) + "</td></tr>" +
                "<tr><th>Partner SPOC</th><td>" + safe(poc.getPartnerSpoc()) + "</td></tr>" +
                "<tr><th>Partner SPOC Email</th><td>" + safe(poc.getPartnerSpocEmail()) + "</td></tr>" +
                "<tr><th>Partner Designation</th><td>" + safe(poc.getPartnerDesignation()) + "</td></tr>" +
                "<tr><th>Partner Mobile</th><td>" + safe(poc.getPartnerMobileNumber()) + "</td></tr>";
        }

        body +=
                "</table>" +
                "<p class='success'>✅ Created successfully! 🚀</p>" +
                "</body></html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(body, true); // true = HTML

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    private String safe(Object value) {
        return value != null ? value.toString() : "";
    }
}
