

package com.lyve.test;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;

import java.util.HashMap;
import java.util.Map;

public class TestTwilioCall {

    public static final String ACCOUNT_SID = "ACf3ea7934f4c52c30409ac744e9bbee7d";
    public static final String AUTH_TOKEN = "f43128da1fa2e67ec1329e80e7d6437c";

    public static void main(String[] args) throws TwilioRestException {

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", "(408) 260-5076"); // Replace with your phone number
        callParams.put("From", "(408) 260-5076"); // Replace with a Twilio number
        callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
        // Make the call
        Call call = callFactory.create(callParams);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getCallerName());
    }
}
