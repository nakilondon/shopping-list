package net.lewes.shoppinglist.controllers;


// Java Program to Create Rest Controller that
// Defines various API for Sending Mail

// Importing required classes

import net.lewes.shoppinglist.models.EmailDetails;
import net.lewes.shoppinglist.models.Product;
import net.lewes.shoppinglist.repositories.ProductRepository;
import net.lewes.shoppinglist.services.EmailService;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
// Class
public class EmailController {

    private final EmailService emailService;
    private final ProductRepository productRepository;

    public EmailController(EmailService emailService, ProductRepository productRepository) {
        this.emailService = emailService;
        this.productRepository = productRepository;
    }

    // Sending a simple Email
    @CrossOrigin
    @PostMapping("/sendMail")
    public String sendMail()
    {
        System.out.println("email requested");
        String message = "<!DOCTYPE html><html><body><h1>Shopping list</h1><ul>";

        for (Product product: productRepository.findAll()) {
            message += "<li>"+product.getProductName() + "</li>";
        }
        message+="</ul></body></html>";

        EmailDetails details = new EmailDetails("email@test.com", message, "Shopping List");

        String status = emailService.sendMailWithAttachment(details);
                //= emailService.sendSimpleMail(details);

        System.out.println(status);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }
}