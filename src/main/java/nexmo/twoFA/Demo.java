package nexmo.twoFA;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Demo 
{
	
    public static void main( String[] args )
    {
    	TwoFactorAuth verifyClass= new TwoFactorAuth();
    	Scanner scanner = new Scanner(System.in);
    	verifyClass.initClient();
    	
    	System.out.println("Enter your phone number");
    	String phoneNumber= scanner.nextLine();
    	verifyClass.sendCode(phoneNumber);
    	
    	
    	System.out.println("Please check & enter verification code");
    	
    	String verificationCode= scanner.nextLine();
    	boolean status = verifyClass.verifyCode(verificationCode);
    	if(status) {
    		System.out.println("Phone number verification Success");
    	}else {
    		System.out.println("Phone number verification Failure");
    	}
    	
    }
}
