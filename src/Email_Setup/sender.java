package Email_Setup;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


/**
 * This is the main sender class which is used to send emil to clients using SMTP server
 * This class is using javax.mail library
 * reference : https://javaee.github.io/javamail/
 * @author Prabdeep Singh Pannu
 */

public class sender extends javax.mail.Authenticator {
    private String host = "smtp.gmail.com";
    private String u;
    private String p;
    private Session s;


    public sender(String u, String p) {
        this.u = u;
        this.p = p;


        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.host", host);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.socketFactory.port", "");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.quitwait", "false");

        s = Session.getDefaultInstance(prop, this);
    }


    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(u, p);


    }

    public synchronized void sendMail(String subject, String body,
                                      String sender, String recipients) throws Exception {
        MimeMessage m = new MimeMessage(s);
        DataHandler handler = new DataHandler(new BS(body.getBytes(), "text/plain"));
        m.setSender(new InternetAddress(sender));
        m.setSubject(subject);
        m.setDataHandler(handler);

        if (recipients.indexOf(',') > 0)
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

        Transport.send(m);
    }

    class BS implements DataSource {
        private byte[] d;
        private String t;

        public BS(byte[] d, String t) {
            super();
            this.d = d;
            this.t = t;
        }

        public String getContentType() {
            if (t == null)
                return "application/octet-stream";
            else
                return t;
        }

        @Override
        public String getName() {
            return null;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(d);
        }


        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }


}
