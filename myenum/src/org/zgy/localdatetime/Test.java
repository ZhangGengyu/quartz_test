package org.zgy.localdatetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {

        /*Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        int second = instance.get(Calendar.SECOND);
        System.out.println(hour + ":" + minute + ":" + second);*/

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String format = sdf.format(date);
        Date parse = sdf.parse("3:00:00");

//        System.out.println(date);
        System.out.println(parse.getTime());
//        System.out.println(date.getTime());
//        System.out.println(new Date(30812000L));
    }
}
