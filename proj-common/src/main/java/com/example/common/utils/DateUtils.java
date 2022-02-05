package com.example.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhuchao
 * @date 2022/2/5 11:26 下午
 */
@Slf4j
public class DateUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static final String DATE_LOCAL_US = "US";

    public static final String DATE_T_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String DATE_T_Z_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static final String DATE_YYYYMMDD = "yyyyMMdd";

    public static final String DATE_YYMMDD = "yyMMdd";

    public static final String DATE_F_MMDDYYYY = "MM/dd/yyyy";

    public static final String DATE_DDMMMYY = "ddMMMyy";

    public static final String DATE_F_YYYYMD = "yyyy/M/d";

    public static final String DATE_F_YYYYMDHH_MM_SS = "yyyy/M/d HH:mm:ss";

    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String DATE_YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_YYYY_MM_DD_HH_MM_ss_S = "yyyy-MM-dd HH:mm:ss.S";

    public static final String DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String TIME_HHmm = "HHmm";

    public static final String TIME_HH_mm = "HH:mm";

    public static final String TIME_HH_mm_ss = "HH:mm:ss";

    public static final String TIME_S_HH_mm_ss = "HHmmss";

    public static final String KEY_MAC = "HmacMD5";


    public static int string2int(String number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.intValue();
    }

    /**
     * 将指定的(日期/时间)格式转换成另一种指定的格式
     */
    public static String dateTimeFormat(String dateTime, String originFormat, String newFormat) {
        return dateTimeFormat(dateTime, originFormat, newFormat, 0L);
    }

    /**
     * 将指定的(日期/时间)格式转换成另一种指定的格式
     *
     * @param diffTime 间隔时间，单位：秒
     */
    public static String dateTimeFormat(String dateTime, String originFormat, String newFormat, long diffTime) {
        String formatDateTime = "";

        if (StringUtils.isNotBlank(dateTime)) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(originFormat));
            formatDateTime = localDateTime.plusSeconds(diffTime).format(DateTimeFormatter.ofPattern(newFormat));
        }

        return formatDateTime;
    }

    /**
     * 将指定的(日期)格式转换成另一种指定的格式
     */
    public static String dateFormat(String date, String originFormat, String newFormat) {
        String formatDate = "";

        if (StringUtils.isNotBlank(date)) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(originFormat));
            formatDate = localDate.format(DateTimeFormatter.ofPattern(newFormat));
        }

        return formatDate;
    }

    /**
     * 获取当前的日期时间
     */
    public static String nowDateTime(String pattern) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));

        return now;
    }

    /**
     * 获取当前的日期
     */
    public static String nowDate(String pattern) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));

        return now;
    }

    public static String dateFormatWithLocal(String date, String originFormat, String newFormat, String local) {
        String formatDate = "";

        if (StringUtils.isNotBlank(date)) {
            final SimpleDateFormat oldFormatter = new SimpleDateFormat(originFormat);
            final SimpleDateFormat newFormatter = new SimpleDateFormat(newFormat, new Locale(local));
            try {
                formatDate = newFormatter.format(oldFormatter.parse(date)).toUpperCase();
            } catch (ParseException e) {
                log.error("解析日期出错", e);
            }
        }

        return formatDate;
    }

    /**
     * 将一个日期加上或减去daysToAdd天数,得出新的日期
     */
    public static String calcDate(String date, String originFormat, long daysToAdd, String newFormat) {
        String formatDate = "";

        if (StringUtils.isNotBlank(date)) {
            // 将原始的日期转换成localDate
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(originFormat));

            // 计算日期
            LocalDate _localDate = localDate.plusDays(daysToAdd);

            // 将计算完的日期转换成需要的格式
            formatDate = _localDate.format(DateTimeFormatter.ofPattern(newFormat));
        }

        return formatDate;
    }

    public static String calcDateTime(String date, String originFormat, long daysToAdd, String newFormat) {
        String formatDate = "";

        if (StringUtils.isNotBlank(date)) {
            // 将原始的日期转换成localDate
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(originFormat));

            // 计算日期
            LocalDateTime _localDateTime = localDateTime.plusDays(daysToAdd);

            // 将计算完的日期转换成需要的格式
            formatDate = _localDateTime.format(DateTimeFormatter.ofPattern(newFormat));
        }

        return formatDate;
    }

    /**
     * 将指定的(时间)格式转化成另一种指定的格式
     */
    public static String timeFormat(String time, String originFormat, String newFormat) {
        String formatTime = "";

        if (StringUtils.isNotBlank(time)) {
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern(originFormat));
            formatTime = localTime.format(DateTimeFormatter.ofPattern(newFormat));
        }

        return formatTime;
    }

    /**
     * 将millisecods 转换成format的时间
     */
    public static String long2Time(long milliSencond, String format) {
        Instant instant = Instant.ofEpochMilli(milliSencond);
        DateTimeFormatter formatterSS2WithZone = DateTimeFormatter.ofPattern(format).withZone(
                ZoneId.systemDefault());

        return formatterSS2WithZone.format(instant);
    }


}
