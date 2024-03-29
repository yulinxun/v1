package com.yu.commons.util;

import java.util.Random;

/**
 * @author yu
 * @date 2019/11/12 0012
 */
public class CodeUtils {
    public static String generateCode(int len){
        int min = Double.valueOf(Math.pow(10, len - 1)).intValue();
        int num = new Random().nextInt(
                Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0,len);
    }
}
