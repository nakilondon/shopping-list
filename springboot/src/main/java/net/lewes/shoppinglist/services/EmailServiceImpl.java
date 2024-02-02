package net.lewes.shoppinglist.services;

import net.lewes.shoppinglist.models.EmailDetails;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());


            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(InternetAddress.parse(details.getRecipient()));
            mimeMessageHelper.setText(details.getMsgBody(),true);
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            if (details.getAttachment() != null) {
                FileSystemResource file
                        = new FileSystemResource(
                        new File(details.getAttachment()));

                mimeMessageHelper.addAttachment(
                        file.getFilename(), file);
            }
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "{\"message\":\"Mail sent Successfully\"}";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "{\"message\":\"Error while sending mail!!!\"}";
        }
    }
}
