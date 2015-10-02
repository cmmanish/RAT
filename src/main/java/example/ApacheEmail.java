package example;

import org.apache.commons.mail.*;

/**
 * Created by mmadhusoodan on 1/8/15.
 */
public class ApacheEmail {

    public void sendSimpleMail() throws Exception {
        Email email = new SimpleEmail();
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("naanu.nane", "hsinam123"));
        email.setDebug(false);
        email.setHostName("smtp.gmail.com");
        email.setFrom("naanu.nane@gmail.com");
        email.setSubject("Hi");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("cmmanish@gmail.com");
        email.setTLS(true);
        //email.setDebug(true);
        email.send();
        System.out.println("Mail sent! ApacheEmail");
    }

    public void sendHtmlEmail() throws Exception {

        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setAuthenticator(new DefaultAuthenticator("naanu.nane", "hsinam123"));

        email.setFrom("naanu.nane@gmail.com");
        email.addTo("cmmanish@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        //email.setDebug(true);
        email.send();
        System.out.println("Sent message successfully....sendHtmlEmail");
    }

    public void sendEmailAttachment(String toEmail, String attachmentName, String reportPath) throws Exception {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();

        attachment.setPath(reportPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName(attachmentName);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setAuthenticator(new DefaultAuthenticator("naanu.nane", "hsinam123"));

        email.setFrom("naanu.nane@gmail.com");
        email.addTo(toEmail);
        email.setSubject("TestMailAttachment");
        email.setMsg("This is a test mail ... :-)");
        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
        System.out.println("Sent message successfully....sendEmailAttachment");
    }

    public static void main(String[] args) throws Exception {


        ApacheEmail email = new ApacheEmail();

        //email.sendSimpleMail();
        email.sendEmailAttachment("mmadhusoodan@lyveminds.com", "2015-01-09Report", "reports/2015-01-09Report.png");
        //email.sendHtmlEmail();
    }
}
