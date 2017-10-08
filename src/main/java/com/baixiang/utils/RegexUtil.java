package com.baixiang.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 获取查询的字符串
     * 将匹配的字符串取出
     */
    public static ArrayList find(String rawStr, String regex) {
        ArrayList<String> matchList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawStr);
        while (matcher.find()) {
            matchList.add(matcher.group());
        }
        return matchList;
    }

    public static String findFirst(String rawStr, String regex) {
        String matchStr = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawStr);
        while (matcher.find()) {
            matchStr = matcher.group();
            break;
        }
        return matchStr;
    }

    /**
     * 字符串的分割
     */
    public static void getDivision(String str, String regx) {
        String[] dataStr = str.split(regx);
        for (String s : dataStr) {
            System.out.println("正则表达式分割++" + s);
        }
    }

    /**
     * 字符串的替换
     */
    public static void getReplace(String str, String regx, String replaceStr) {
        String stri = str.replaceAll(regx, replaceStr);
        System.out.println("正则表达式替换" + stri);
    }

    /**
     * 字符串处理之匹配
     * String类中的match 方法
     */
    public static void getMatch(String str, String regx) {
        System.out.println("正则表达匹配" + str.matches(regx));
    }
}
