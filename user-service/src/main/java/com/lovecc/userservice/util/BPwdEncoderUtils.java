package com.lovecc.userservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdEncoderUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     *
     * @param password
     * @return
     */
    public static String BCryptPassword(String password){
        return encoder.encode(password);
    }

    /**
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
}
