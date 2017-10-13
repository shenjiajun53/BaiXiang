package com.baixiang.utils.logger;

import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenjj on 2017/5/24.
 */
public class LogUtil {
    private static final String TAG = "LogUtil";
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    //    public static final int ASSERT = 7;
    public static final int INFO = 4;
    public static final int TRACE = 2;
    public static final int WARN = 5;

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private static final int CHUNK_SIZE = 4000;

    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

//    public static Logger logger;

    public void test() {

    }

    public static void info(String message) {
        formatLog(null, INFO, message);
    }

    public static void info(Class<?> mClass, String message) {
        Logger logger = LoggerFactory.getLogger(mClass);
        formatLog(logger, INFO, message);
    }

    public static void debug(String message) {
        formatLog(null, DEBUG, message);
    }

    public static void debug(Class<?> mClass, String message) {
        Logger logger = LoggerFactory.getLogger(mClass);
        formatLog(logger, DEBUG, message);
    }

    public static void json(String jsonStr) {
        jsonStr = JsonFormatter.format(JsonFormatter.convertUnicode(jsonStr));
        formatLog(null, INFO, jsonStr);
    }

    public static void json(Class<?> mClass, String jsonStr) {
        Logger logger = LoggerFactory.getLogger(mClass);
        jsonStr = JsonFormatter.format(JsonFormatter.convertUnicode(jsonStr));
        formatLog(logger, INFO, jsonStr);
    }

    private static void formatLog(Logger logger, int type, String str) {
        logTopBorder(logger, type, null);
        logContent(logger, type, null, str);
        logBottomBorder(logger, type, null);
    }

//    public synchronized static void json(Class<?> mClass, Object obj) {
//        logger = LoggerFactory.getLogger(mClass);
//        String jsonStr = "invalid json";
//        if (obj != null) {
//            String formatStr = com.alibaba.fastjson.JSONObject.toJSONString(obj);
//            jsonStr = JsonFormatter.format(JsonFormatter.convertUnicode(formatStr));
//        }
//        logTopBorder(INFO, null);
//        logContent(INFO, null, jsonStr);
//        logBottomBorder(INFO, null);
//    }


//    public synchronized static void printRequest(int logType, String tag, String url, Object params) {
//        logTopBorder(logType, tag);
//        log(logType, tag, HORIZONTAL_DOUBLE_LINE + " request : " + url);
//        logDivider(logType, tag);
//        json(logType, tag, params);
//        logBottomBorder(logType, tag);
//    }
//
//
//    public synchronized static void printResponse(int logType, String tag, String url, Object params) {
//        logTopBorder(logType, tag);
//        log(logType, tag, HORIZONTAL_DOUBLE_LINE + " response : " + url);
//        logDivider(logType, tag);
//        json(logType, tag, params);
//        logBottomBorder(logType, tag);
//    }


    private static void logTopBorder(Logger logger, int logType, String tag) {
        log(logger, logType, tag, TOP_BORDER);
    }


    private static void logBottomBorder(Logger logger, int logType, String tag) {
        log(logger, logType, tag, BOTTOM_BORDER);
    }

    private static void logDivider(Logger logger, int logType, String tag) {
        log(logger, logType, tag, MIDDLE_BORDER);
    }

    private static void logContent(Logger logger, int logType, String tag, String content) {
        if (!TextUtils.isEmpty(content)) {
            String[] lines = content.split(System.getProperty("line.separator"));
            for (String line : lines) {
                log(logger, logType, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
            }
        }
    }

    private static void log(Logger logger, int logType, String tag, String line) {
//        tag = TAG + "-" + tag;
        tag = TAG;
        if (null == logger) {
            logger = LoggerFactory.getLogger(tag);
        }
        switch (logType) {
            case ERROR:
                logger.error(line);
                break;
            case INFO:
                logger.info(line);
                break;
            case TRACE:
                logger.trace(line);
                break;
            case WARN:
                logger.warn(line);
                break;
            case DEBUG:
            default:
                logger.debug(line);
                break;
        }
    }

}
