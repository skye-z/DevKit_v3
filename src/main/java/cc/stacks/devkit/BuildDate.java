package cc.stacks.devkit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// 日期构建工具
public class BuildDate {

    // 获取当前时间戳
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    // 任意格式时间转换为时间戳
    public static String toTimestamp(DatePattern Pattern, Object DateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern.getPattern());
        long timestamp = Long.parseLong(DateTime.toString());
        Date date = new Date(timestamp);
        return simpleDateFormat.format(date);
    }

    // 时间对象转换为字符串
    public static String toString(DatePattern Pattern, Date DateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern.getPattern());
        return simpleDateFormat.format(DateTime);
    }

    // 获取当前小时
    public static String getHour() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(date);
    }

    // 获取偏移小时
    public static String getHour(int Next) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + Next);
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(cal.getTime());
    }

    // 获取一天的开始时间
    public static Date getDayBegin(int Offset) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, Offset);
        return cal.getTime();
    }

    // 获取一天的结束时间
    public static Date getDayEnd(int Offset) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, Offset);
        return cal.getTime();
    }

    // 获取一周的开始时间
    public static Date getBeginDayOfWeek(int Offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek + Offset*7);
        return getDayStartTime(cal.getTime());
    }

    // 获取一周的结束时间
    public static Date getEndDayOfWeek(int Offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek(Offset));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取指定日期的开始时间
    public static Date getDayStartTime(Date DateTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != DateTime)
            calendar.setTime(DateTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // 获取指定日期的结束时间
    public static Date getDayEndTime(Date DateTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != DateTime)
            calendar.setTime(DateTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println("当前时间戳: " + BuildDate.getTimestamp());
        System.out.println("转换时间戳: " + BuildDate.toTimestamp(DatePattern.COMPLETE, getTimestamp()));
        System.out.println("当前小时: " + BuildDate.getHour());
        System.out.println("下个小时: " + BuildDate.getHour(1));
        System.out.println("=========================================");
        System.out.println("今天开始时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getDayBegin(0)));
        System.out.println("今天结束时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getDayEnd(0)));
        System.out.println("明天开始时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getDayBegin(1)));
        System.out.println("明天结束时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getDayEnd(1)));
        System.out.println("=========================================");
        System.out.println("本周开始时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getBeginDayOfWeek(0)));
        System.out.println("本周结束时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getEndDayOfWeek(0)));
        System.out.println("上周开始时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getBeginDayOfWeek(-1)));
        System.out.println("上周结束时间: " + BuildDate.toString(DatePattern.COMPLETE, BuildDate.getEndDayOfWeek(-1)));
    }

}
