package example;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Created by mmadhusoodan on 1/28/15.
 */
public class QaEmail {
    private static Logger log = Logger.getLogger(QaEmail.class);

    public void sendEmailAttachment(String fromEmail, String[] toEmail, String subject, String body, String reportPath) throws Exception {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();

        attachment.setPath(reportPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        String attachmentName = reportPath.split("")[0];
        attachment.setName("PortalAutomation" + reportPath);

        // Create the email message
        MultiPartEmail email = new HtmlEmail();
        email.setHostName("smtp.corp.lyveminds.com");
        //email.setSmtpPort(465);
        //email.setSSL(true);
        //email.setAuthenticator(new DefaultAuthenticator(username, password));

        email.setFrom(fromEmail);

        for (int i = 0; i < toEmail.length; i++) {
            email.addTo(toEmail[i]);
        }
        email.setSubject(subject);
        email.setMsg(body);
        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
        log.info("Sent message successfully....sendEmailAttachment");
    }

    public static void main(String args[]) throws Exception {

        Font font = new Font("Times New Roman", Font.BOLD, 16);

        String fromEmail = "lyvesuite.automation@lyveminds.com";
        String toEmail[] = {"mmadhusoodan@lyveminds.com"};
        String subject = "lyvesuite Automation Test Results";

        String TestrailURL = "https://testrail.blackpearlsystems.com/index.php?/runs/view/14505";


        String body = "<html> <b>Lyve Portal Automation Result:</b>" + "\n\n";
        body += "<b><u>Test Results</b></u></html>" + "\n";
//        body += "For complete execution details, use the link below:\n" + TestrailURL + "\n\n";
//        body += "TestRails login:\n"+ "Username: trguest2@blackpearlsystems.com \nPassword: TRGuestUser2\n ";

        String reportPath = "reports/2015-01-09Report.png";
        log.info(body);
        QaEmail qaEmail = new QaEmail();
        qaEmail.sendEmailAttachment(fromEmail, toEmail, subject, body, reportPath);
    }
}