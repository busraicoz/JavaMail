package http;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author busraicoz
 */

public class Http {
	private static String content = "";

	public String getContent() {
		return content;
	}

	public void sendMail(String[] recipients, String subject, String note) {
		final String username = "*****";
		final String password = "*****";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(" info@microsoft.com"));
			
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.length];

			for (int i = 0; i < recipients.length; i++)
			{
			    addressTo[i] = new javax.mail.internet.InternetAddress(recipients[i]);
			}

			message.setRecipients(javax.mail.Message.RecipientType.TO, addressTo); 
			
			message.setSubject(subject);
			message.setText(note);
			Transport.send(message);

		} catch (AuthenticationFailedException e) {
			System.out.println("Wrong UserName or Password");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public void controlfromfile(String fname, String icerik) {
		BufferedReader reader = null;

		try {
			Http a = new Http();
			File file = new File(fname);
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				a.httpControl(line);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void httpControl(String website) {
		try {
			URL url = new URL(website);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			int statusCode = http.getResponseCode();
			System.out.println(website);
			System.out.println(statusCode);

			if (statusCode >= 500 && statusCode < 600) {
				content += "/n" + website + " page's status code: "
						+ statusCode;
			}
                        
                        
		} catch (MalformedURLException e) {
			System.out.println(website);
			System.out.println("incorrect address");

		} catch (UnknownHostException e) {
			System.out.println(website);
			System.out.println("incorrect or incomplete address");
			content += "\n" + website + " " + " UnknownHostException";

		} catch (Exception e) {
			System.out.println("ERROR!!!!");
		}
	}
}

 
		
		
	
