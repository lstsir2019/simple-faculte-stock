/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.commun.util;

import java.math.BigDecimal;

/**
 *
 * @author Anas
 */
public class NumberUtil {

    private static final String CHAINE_VIDE = "";

    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Double toDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0D;
        } else {
            return Double.parseDouble(value);
        }
    }

    public static Integer toInteger(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public static Long toLong(String value) {
        if (value == null || value.isEmpty()) {
            return 0L;
        } else {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public static BigDecimal toBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(value);
        }
    }

    public static String toString(BigDecimal value) {
        if (value == null) {
            return CHAINE_VIDE;
        } else {
            return String.valueOf(value);
        }
    }

    public static String toString(Integer value) {
        if (value == null) {
            return CHAINE_VIDE;
        } else {
            return String.valueOf(value);
        }
    }
}
