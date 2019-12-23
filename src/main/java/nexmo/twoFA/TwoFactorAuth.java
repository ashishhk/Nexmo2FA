package nexmo.twoFA;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientCreationException;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.NexmoResponseParseException;
import com.nexmo.client.verify.CheckResponse;
import com.nexmo.client.verify.VerifyResponse;


public class TwoFactorAuth {
	
	public final static String API_KEY = "#####";
	
	public final static String API_SECRET = "#######";
	
	public final static int STATUS_CODE_SUCCESS=0;
	
	NexmoClient client;
	
	VerifyResponse ongoingVerify;
	
	void initClient(){
		try {
		client = NexmoClient.builder()
    	        .apiKey(API_KEY)
    	        .apiSecret(API_SECRET)
    	        .build();
		}
		catch(NexmoClientCreationException e) {
			System.out.println("credentials aren't provided in a valid pairing or there were issues generating an");
		}
	}
	
	void sendCode(String toNumber){
		try{
    		ongoingVerify = client.getVerifyClient().verify(toNumber, "NEXMO");
    	}
		catch(NexmoClientException e) {
    		System.out.println("There was a problem with the Nexmo request or response objects.");
    	}
    	catch(NexmoResponseParseException e) {
    		System.out.println("The response from the API could not be parsed.");
    	}
	}
	
	boolean verifyCode(String code) {
		CheckResponse response = null;
    	try{
    		response = client.getVerifyClient().check(ongoingVerify.getRequestId(), code);
    	}
    	catch(NexmoClientException e) {
    		System.out.println("There was a problem with the Nexmo request or response objects.");
    	}
    	catch(NexmoResponseParseException e) {
    		System.out.println("The response from the API could not be parsed.");
    	}
    	
    	return response.getStatus().getVerifyStatus()==STATUS_CODE_SUCCESS;
 	}
}
