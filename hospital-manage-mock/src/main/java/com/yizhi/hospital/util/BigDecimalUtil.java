package com.yizhi.hospital.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * The type Big decimal util.
 */
public class BigDecimalUtil {

    //默认运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * Add double.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the double
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * Add double.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the double
     */
    public static double add(double v1, double v2, int scale) {
        return round(add(v1, v2), 2);
    }

    /**
     * Add string.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the string
     */
    public static String add(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) v1 = "0";
        if (StringUtils.isEmpty(v2)) v2 = "0";
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * Add string.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the string
     */
    public static String add(String v1, String v2, int scale) {
        if (StringUtils.isEmpty(v1)) v1 = "0";
        if (StringUtils.isEmpty(v2)) v2 = "0";
        return round(add(v1, v2), 2);
    }

    /**
     * Subtract double.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the double
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * Subtract double.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the double
     */
    public static double subtract(double v1, double v2, int scale) {
        return round(subtract(v1, v2), scale);
    }

    /**
     * Subtract string.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the string
     */
    public static String subtract(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) v1 = "0";
        if (StringUtils.isEmpty(v2)) v2 = "0";
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    /**
     * Subtract string.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the string
     */
    public static String subtract(String v1, String v2, int scale) {
        return round(subtract(v1, v2), 2);
    }

    /**
     * Multiply double.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the double
     */
    public static double multiply(double v1, double v2, int scale) {
        return round(multiply(v1, v2), scale);
    }

    /**
     * Multiply double.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the double
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * Multiply string.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the string
     */
    public static String multiply(String v1, String v2, int scale) {
        return round(multiply(v1, v2), scale);
    }

    /**
     * Multiply string.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the string
     */
    public static String multiply(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) v1 = "0";
        if (StringUtils.isEmpty(v2)) v2 = "0";
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * Divide double.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the double
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * Divide double.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the double
     */
    public static double divide(double v1, double v2, int scale) {
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Divide double.
     *
     * @param v1         the v 1
     * @param v2         the v 2
     * @param scale      the scale
     * @param round_mode the round mode
     * @return the double
     */
    public static double divide(double v1, double v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, round_mode).doubleValue();
    }

    /**
     * Divide string.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the string
     */
    public static String divide(String v1, String v2) {
        if (StringUtils.isEmpty(v1)) v1 = "0";
        if (StringUtils.isEmpty(v2) || Double.parseDouble(v2) == 0) v2 = "1";
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * Divide string.
     *
     * @param v1    the v 1
     * @param v2    the v 2
     * @param scale the scale
     * @return the string
     */
    public static String divide(String v1, String v2, int scale) {
        if (StringUtils.isEmpty(v2) || Double.parseDouble(v2) == 0) v2 = "1";
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Divide string.
     *
     * @param v1         the v 1
     * @param v2         the v 2
     * @param scale      the scale
     * @param round_mode the round mode
     * @return the string
     */
    public static String divide(String v1, String v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round_mode).toString();
    }

    /**
     * Round double.
     *
     * @param v     the v
     * @param scale the scale
     * @return the double
     */
    public static double round(double v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Round double.
     *
     * @param v          the v
     * @param scale      the scale
     * @param round_mode the round mode
     * @return the double
     */
    public static double round(double v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, round_mode).doubleValue();
    }

    /**
     * Round string.
     *
     * @param v     the v
     * @param scale the scale
     * @return the string
     */
    public static String round(String v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Round string.
     *
     * @param v          the v
     * @param scale      the scale
     * @param round_mode the round mode
     * @return the string
     */
    public static String round(String v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, round_mode).toString();
    }
}
