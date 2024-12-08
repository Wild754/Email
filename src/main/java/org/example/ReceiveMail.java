package org.example;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import com.sun.mail.pop3.POP3Store;

public class ReceiveMail{

    public static void receiveEmail(String pop3Host, String storeType,
                                    String user, String password) {
        try {
            // 1) Set properties
            Properties properties = new Properties();
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.ssl.enable", "true"); // Enable SSL

            // 2) Get the session object
            Session emailSession = Session.getDefaultInstance(properties);

            // 3) Connect to the POP3 store
            POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
            emailStore.connect(user, password);

            // 4) Access the INBOX folder
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // 5) Retrieve and print messages
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Subject: " + message.getSubject());
            }

            // 6) Close the folder and store
            emailFolder.close(false);
            emailStore.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";//change accordingly
        String mailStoreType = "pop3";
        String username= "xxx";//change accordingly
        String password= "xxx";//change accordingly

        receiveEmail(host, mailStoreType, username, password);

    }
}
