package com.ownerpro.web.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author yannis
 * @version 2020/11/7 9:40
 */
@Component
public class TimeUtil {

    public static Timestamp getCurrentTimestamp(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }


    public static String getCurrentTimestampForWxSend(String time){
        time = time.substring(0,16);
        StringBuilder sb = new StringBuilder(time);
        sb.replace(4,5,"年");
        sb.replace(7,8,"月");
        sb.replace(10,10,"日");
        return sb.toString();
    }

}
