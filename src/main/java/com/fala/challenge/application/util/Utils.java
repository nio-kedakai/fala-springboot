package com.fala.challenge.application.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
    }

    public static String getLocalDateTimeNowFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String sessionExpires(Long minutesForExpire) {
        LocalDateTime now = getLocalDateTimeNow();
        return now.plusMinutes(minutesForExpire).toString();
    }


}
