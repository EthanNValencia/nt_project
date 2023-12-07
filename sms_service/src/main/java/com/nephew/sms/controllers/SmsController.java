package com.nephew.sms.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

	// NT Approved: Your appointment on [appointment.date], from [appointment.beginTime] to [appointment.endTime] has been approved. [employee.name] is excited to meet you at [office.address]. Need to reschedule or have a question? Call [office.phoneNumber]. 
	// NT Reminder: You have an appointment, today, from [appointment.beginTime] to [appointment.endTime]. [employee.name] is excited to meet you at [office.address]. Need to reschedule or have a question? Call [office.phoneNumber]. 
	// NT Welcome: Your appointment at [company.name] is now pending. We will contact you when changes are made to your appointment status. If you believe this message was sent in error, please respond STOP to opt-out. 
	
	
	// Find your Account SID and Auth Token at twilio.com/console
	// sudo nano /etc/environment
	// TWILIO_ACCOUNT_SID=""
	// TWILIO_AUTH_TOKEN=""
	// TWILIO_PHONE_NUMBER=""
	// Nano commands: 
	// Ctrl + O (save)
	// Enter (confirm file)
	// Ctrl + X (exit)
	// ** Restart Required **
	
	// +17573491598
	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
	private static final String TWILIO_PN = System.getenv("TWILIO_PHONE_NUMBER");

	public static void main(String[] args) {
		printEnvironmentVariables();
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(new PhoneNumber("+16165664966"),
				new PhoneNumber("+17573491598"), "This is a test! Can you see me?").create();

		System.out.println(message.toString());
		
	}

	private static void printEnvironmentVariables() {
		System.out.println(ACCOUNT_SID);
		System.out.println(AUTH_TOKEN);
		System.out.println(TWILIO_PN);
	}

}
/* 
Message(
body=This is a test! Can you see me?, numSegments=1, direction=outbound-api, from=+17573491598, to=+16165664966, 
dateUpdated=2023-11-08T18:01:27Z, price=null, errorMessage=null, uri=/2010-04-01/Accounts/AC791841a050877b0165b5730fa4126788/Messages/SM9cde7d6fa68141f1cf076a9fdbc5c94b.json, 
accountSid=AC791841a050877b0165b5730fa4126788, numMedia=0, status=queued, messagingServiceSid=null, sid=SM9cde7d6fa68141f1cf076a9fdbc5c94b, dateSent=null, 
dateCreated=2023-11-08T18:01:27Z, errorCode=null, priceUnit=USD, apiVersion=2010-04-01, 
subresourceUris={media=/2010-04-01/Accounts/AC791841a050877b0165b5730fa4126788/Messages/SM9cde7d6fa68141f1cf076a9fdbc5c94b/Media.json})
*/