package bank.management.system;

public class OTPVerficaTion {
    public static boolean verifyOTP(String enteredOTP, String generatedOTP) {
        return enteredOTP.equals(generatedOTP);
    }
}
