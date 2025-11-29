package Util;

import java.security.SecureRandom;

public class OtpUtils {
    private static final SecureRandom random=new SecureRandom();

    public static String generateOtp(){
        int otp=1000+ random.nextInt(9000);
        return String.valueOf(otp);
    }
}
