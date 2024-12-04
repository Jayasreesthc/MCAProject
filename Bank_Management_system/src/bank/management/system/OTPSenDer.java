package bank.management.system;

//    import com.mysql.cj.protocol.Message;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class OTPSenDer {
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";

    public static void sendOTP(String phoneNumber, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber(phoneNumber), // to
                        new PhoneNumber("your_twilio_phone_number"), // from
                        "Your OTP is: " + otp)
                .create();

        System.out.println("OTP sent: " + message.getSid());
    }
}
