package com.edu.nju.seckill.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiuWen
 * @version 1.0
 * @name ValidatorUtils
 * @description 各种验证的小工具
 * @date 2020-4-15 0:38
 */
public class ValidatorUtils {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|" +
            "([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");

    public static boolean isMobile(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Matcher m = MOBILE_PATTERN.matcher(str);
        return m.matches();
    }
}
