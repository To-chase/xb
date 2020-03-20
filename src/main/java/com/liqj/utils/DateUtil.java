package com.liqj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Liqj
 * @date 2020/3/18 17:38
 */
public class DateUtil {
    public static String dateStr(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }
}
