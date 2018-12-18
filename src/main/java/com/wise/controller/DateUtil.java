package com.wise.controller;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author YsCY丶
 * @Date 2018/12/11 11:36
 * @Version 1.0
 **/
public class DateUtil {

    private boolean holidayFlag; //   int[][] holidays = {{6, 9}, {6, 10}, {9, 15}, {9, 16}};

    HashMap<Integer, List<Integer>> holidays;

    public void getHolidays() { //      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        if (holidays == null) {
            holidays = new HashMap<>();
            holidays.put(6, Arrays.asList(9, 10));
            holidays.put(9, Arrays.asList(15, 16));
            holidays.put(1, Arrays.asList(1));
        }
    }

    /**
     * 计算工作日, 具体节日包含哪些,可以在HolidayMap中修改
     * src =日期(源), adddays 要加的天数
     */
    public Calendar addDateByWorkDay(Calendar src, int adddays) { //        Calendar result = null;
        holidayFlag = false;
        for (int i = 0; i < adddays; i++) { //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, 1);
            holidayFlag = checkHoliday(src);
            if (holidayFlag) {
                i--;
            }
            System.out.println(src.getTime());
        }
        System.out.println("Final Result:" + src.getTime());
        return src;
    }



    public Integer getToday_old(Calendar src, String format) {
        if (format == null) {
            format = "yyyyMMdd";
        }
        boolean holidayFlag = true;
        while (holidayFlag) { //把源日期加一天
            holidayFlag = checkHoliday(src);
            if (holidayFlag) {src.add(Calendar.DAY_OF_MONTH, -1);}
        }
        Date dNow = src.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dNow); //格式化前3月的时间
        return Integer.parseInt(defaultStartDate); //   System.out.println("生成的时间是：" + defaultEndDate);
    }

    public int beforeDateByWorkDay(Calendar src, int beforedays, String format) {
        if (format == null) {
            format = ("yyyyMMdd");
        } //        Calendar result = null;
        boolean holidayFlag = true;
        while (holidayFlag) { //把源日期加一天
            holidayFlag = checkHoliday(src);
            if (holidayFlag) src.add(Calendar.DAY_OF_MONTH, -1);
        }
        for (int i = 0; i < beforedays; i++) { //把源日期加一天
            src.add(Calendar.DAY_OF_MONTH, -1);
            holidayFlag = true;
            while (holidayFlag) { //把源日期加一天
                holidayFlag = checkHoliday(src);
                if (holidayFlag) {src.add(Calendar.DAY_OF_MONTH, -1);}
            }
            System.out.println(src.getTime());
        }
        System.out.println("Final Result:" + src.getTime());
        Date dBefore = src.getTime(); //得到前3月的时间

        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        return Integer.parseInt(defaultStartDate);
    }

    public int beforeDateByWeek(Calendar src, int beforeweeks, String format) {
        if (format == null) {
            format = ("yyyyMMdd");
        }
        src.add(Calendar.WEEK_OF_MONTH, -beforeweeks);
        src.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
        Date dBefore = src.getTime(); //得到前3月的时间

        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        return Integer.parseInt(defaultStartDate);
    }

    public int beforeDateByMonth(Calendar src, int beforeweeks, String format) {
        if (format == null) {
            format = ("yyyyMMdd");
        }
        src.add(Calendar.MONTH, -beforeweeks);
        System.out.println("Final Result:" + src.getTime());
        Date dBefore = src.getTime(); //得到前3月的时间

        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        return Integer.parseInt(defaultStartDate);
    }


    /**
     * 校验指定的日期是否在节日列表中
     * 具体节日包含哪些,可以在HolidayMap中修改
     *
     * @param src 要校验的日期(源)
     * @author shenjunjie
     */
    public boolean checkHoliday(Calendar src) {
        boolean result = false;
        getHolidays(); //先检查是否是周六周日(有些国家是周五周六)
        if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        if (holidays.containsKey(src.get(Calendar.MONTH) + 1)) {
            if (holidays.get(src.get(Calendar.MONTH) + 1).contains(src.get(Calendar.DAY_OF_MONTH))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化节日List,请在这里添加新的节日
     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
     * 注:年份可以随便写,因为比的时候只比月份和天
     *
     * @author shenjunjie
     */
//   private void initHolidayList() {
//      holidayList = new SparseArray<>();
//
//      //五一劳动节
//      Calendar may1 = Calendar.getInstance();
//      may1.set(Calendar.MONTH, Calendar.MAY);
//      may1.set(Calendar.DAY_OF_MONTH, 1);
//      holidayList.put(holidayList.size(), may1);
//
//      Calendar may2 = Calendar.getInstance();
//      may2.set(Calendar.MONTH, Calendar.MAY);
//      may2.set(Calendar.DAY_OF_MONTH, 2);
//      holidayList.put(holidayList.size(), may2);
//
//      Calendar may3 = Calendar.getInstance();
//      may3.set(Calendar.MONTH, Calendar.MAY);
//      may3.set(Calendar.DAY_OF_MONTH, 3);
//      holidayList.put(holidayList.size(), may3);
//
//      Calendar h3 = Calendar.getInstance();
//      h3.set(2000, 1, 1);
//      holidayList.put(holidayList.size(), h3);
//
//      Calendar h4 = Calendar.getInstance();
//      h4.set(2000, 12, 25);
//      holidayList.put(holidayList.size(), h4);
//
//      //端午节
//      Calendar h5 = Calendar.getInstance();
//      h5.set(2000, 6, 9);
//      holidayList.put(holidayList.size(), h5);
//      Calendar h6 = Calendar.getInstance();
//      h6.set(2000, 6, 10);
//      holidayList.put(holidayList.size(), h6);
//
//      Calendar h7 = Calendar.getInstance();
//      h7.set(2000, 9, 15);
//      holidayList.put(holidayList.size(), h7);
//      Calendar h8 = Calendar.getInstance();
//      h8.set(2000, 9, 16);
//      holidayList.put(holidayList.size(), h8);
//
//      //中国母亲节：五月的第二个星期日
//      Calendar may5 = Calendar.getInstance();
//      //设置月份为5月
//      may5.set(Calendar.MONTH, Calendar.MAY);
//      //设置星期:第2个星期
//      may5.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
//      //星期日
//      may5.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
////        System.out.println(may5.getTime());
//      holidayList.put(holidayList.size(), may5);
//   }
    public void testAddDateByWorkDay() {
        Calendar src = Calendar.getInstance();
        src.set(2010, Calendar.APRIL, 29);
        Calendar result = addDateByWorkDay(src, 2);
        Calendar expected = Calendar.getInstance();
        expected.set(2010, Calendar.MAY, 4); //        Assert.assertEquals(expected.getTime().toString(), result.getTime().toString());
    }

    public void testAddDateByWorkDay2() { //测试母亲节
        Calendar src = Calendar.getInstance();
        src.set(2010, Calendar.MAY, 7);
        Calendar result = addDateByWorkDay(src, 2);
        Calendar expected = Calendar.getInstance();
        expected.set(2010, Calendar.MAY, 11); //        Assert.assertEquals(expected.getTime().toString(), result.getTime().toString());
    }

    /**
     * 获得所在日期的周五日期
     */
    public int getFriday(Calendar date, String format) {
        if (format == null) {
            format = ("yyyyMMdd");
        }
        date.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
        Date dBefore = date.getTime(); //得到前3月的时间
        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        return Integer.parseInt(defaultStartDate);
    }

    /**
     * 获得上周五日期
     */
    public int getLastFriday(Calendar date, String format) {
        if (format == null) {
            format = ("yyyyMMdd");
        }
        date.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
        date.add(Calendar.DATE, -7);
        Date dBefore = date.getTime(); //得到前3月的时间
        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        return Integer.parseInt(defaultStartDate);
    }

    public static void main(String[] args) {
        Calendar may1 = Calendar.getInstance();
        may1.set(Calendar.MONTH, Calendar.JUNE);
        may1.set(Calendar.DAY_OF_MONTH, 11);
        Calendar cal = Calendar.getInstance();
        cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
        cal.add(Calendar.DATE, -7); // 也可以写成:cal.set(GregorianCalendar.DAY_OF_WEEK,6);
        Date dBefore = cal.getTime(); //得到前3月的时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        int aa = Integer.parseInt(defaultStartDate); //        src.set(2016, Calendar.APRIL , 1);
        String date = "20160412";
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
        int maxdate = new DateUtil().getFriday(cal, null);
        aa = new DateUtil().beforeDateByWorkDay(cal, 60, null);
        Calendar expected = Calendar.getInstance();
        expected.set(2010, Calendar.MAY, 4);
    }
}
