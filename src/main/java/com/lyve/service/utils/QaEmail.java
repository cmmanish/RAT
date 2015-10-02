package com.lyve.service.utils;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

/**
 * Created by mmadhusoodan on 1/8/15.
 */
public class QaEmail {
    private static Logger log = Logger.getLogger(QaEmail.class);

    public void sendEmailAttachment(String fromEmail, String senderName, String toEmail, String toName, String subject,  String body, String reportPath) throws Exception {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();

        attachment.setPath(reportPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName("Lyve Suite Automation"+reportPath);

        // Create the email message
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("smtp.corp.lyveminds.com");

        email.setFrom(fromEmail,senderName);
        email.addTo(toEmail, toName);

        email.setSubject(subject);

        email.setMsg(body);
        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
        log.info("Sent message successfully....sendEmailAttachment");
    }
}