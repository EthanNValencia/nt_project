package com.nephew.es.emailservice;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailTest {
	
	private static final String SENDGRID = System.getenv("SENDGRID_API_KEY");
	
	  public static void main(String[] args) throws IOException {
		    Email from = new Email("nephewtechnologies@gmail.com");
		    String subject = "Sending with SendGrid is Fun";
		    Email to = new Email("ejnephew@yahoo.com");
		    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
		    
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(SENDGRID);
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		    } catch (IOException ex) {
		      throw ex;
		    }
		  }
}
