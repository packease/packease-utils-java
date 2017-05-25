package com.aboutcoder.packease.utils.string;

import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 11:54 PM<br />
 * @description <br />
 */
public class PEStringUtils {
    /**
     * Check whether the str is empty.
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether the str isn't empty.
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str == null || str.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Split a string into array.
     *
     * @param str
     * @param expr
     * @return
     */
    public static String[] stringToArray(String str, String expr){
        return str.split(expr);
    }

    /**
     * Integrate array items into a single string with specific pattern.
     *
     * @param arr
     * @param expr
     * @return
     */
    public static String arrayToString(String[] arr,String expr){
        String strInfo = "";
        if(arr != null && arr.length > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for(String str : arr){
                stringBuilder.append(str);
                stringBuilder.append(expr);
            }
            strInfo = stringBuilder.substring(0, stringBuilder.length()-1);
        }
        return strInfo;
    }

    /**
     * Integrate list items into a single string with specific pattern.
     * This function's generic parameter now supports Number and String only,
     * otherwise it will throw an IllegalArgumentException.
     *
     * @param list
     * @param expr
     * @return
     */
    public static <T> String listToString(List<T> list, String expr){
        String strInfo = "";
        if(list != null && list.size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for(T str : list){
                if (str instanceof String) {
                    stringBuilder.append(str);
                    stringBuilder.append(expr);
                } else if (str instanceof Number) {
                    stringBuilder.append(str);
                    stringBuilder.append(expr);
                } else {
                    throw new IllegalArgumentException("List contains illegal or inappropriate arguments.");
                }
            }
            strInfo = stringBuilder.substring(0, stringBuilder.length()-1);
        }
        return strInfo;
    }
}
