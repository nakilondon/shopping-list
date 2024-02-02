package net.lewes.shoppinglist.models;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    public EmailDetails(String recipient, String msgBody, String subject) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
    }
}
