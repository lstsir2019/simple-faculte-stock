/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.commun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Anas
 */
public class DateUtil {

    private DateUtil(){
        throw new IllegalStateException("Utility class");
    }
    //convert Date to String
    public static String formateDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return "";
        }
    }

    //convert String to date
    public static java.util.Date parseDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return (simpleDateFormat.parse(date));
        } catch (ParseException e) {
            return null;
        }
    }
}
