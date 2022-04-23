package com.ownerpro.web.util;

/**
 * @author rai
 * @version 2022/4/18
 */

public class MailUtil {
    //get a random number from 000000 to 999999 and convert to String type
    public static int getRamdomNum(){
        int num = (int)(Math.random()*1000000);
        return num;
    }
    //check if the email's format is correct
    public static boolean checkEmail(String email){
        if(email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            return true;
        }
        return false;
    }
}
