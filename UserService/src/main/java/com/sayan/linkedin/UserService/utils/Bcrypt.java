package com.sayan.linkedin.UserService.utils;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

    public static String hash(String s){
        return BCrypt.hashpw(s,BCrypt.gensalt());
    }

    public static  boolean check(String s, String hashedS){
        return BCrypt.checkpw(s,hashedS);

    }
}
