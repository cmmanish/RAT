package com.lyve.test;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmadhusoodan on 4/14/15.
 */
public class TestTwilioExample {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACf3ea7934f4c52c30409ac744e9bbee7d";
    public static final String AUTH_TOKEN = "f43128da1fa2e67ec1329e80e7d6437c";

    public static void main(String[] args) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", "Twilio is awesome <3"));
        params.add(new BasicNameValuePair("To", "+(408) 260-5076"));
        params.add(new BasicNameValuePair("From", "+(408) 260-5076"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getBody());
    }
}