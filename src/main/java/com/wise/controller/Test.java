package com.wise.controller;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.wise.entity.Dept;
import com.wise.entity.User;
import com.wise.entity.Wtime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");

    /**
     * 姓名 部门  本月应出勤(即获取当月工作日)实际出勤(即到岗次数)本月迟到 本月早退
     * step1 获取某月工作日 ok
     * step2 员工出勤次数 按天数产生记录的
     * step3 迟到  给定区间之后产生记录 符合++
     * step4 早退  给定区间之前产生记录 符合++
     */
    public static int getWorkDays(String src)
    {
        Date date = null;
        try {
            date = simpleDateFormat.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int year = Integer.parseInt(src.substring(0,4));
        int month = Integer.parseInt(src.substring(4,6));
        Calendar setDate = Calendar.getInstance();
        setDate.set(Calendar.YEAR, year);
        setDate.set(Calendar.MONTH, month - 1);
        //从第一天开始
        int monthDay = getMonthDays(date);
        for (int day = 1; day <= monthDay; day++)
        {
            setDate.set(Calendar.DATE,day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")||"星期六".equals(str))
            {
                 sundays++;
            }else {//获取工作日日期
                 date = setDate.getTime();
                 //System.out.println(sd.format(date));
                 System.out.println(date);
            }
        }
        return monthDay - sundays;
    }

    public static void main(String[] args) {

        System.out.println(getWorkDays("201901"));
    }

    /**
     * @param date
     * @return 某年某月总共天数
     */
    public static int getMonthDays(Date date){
        String src =  simpleDateFormat.format(date);
        int year = Integer.parseInt(src.substring(0,4));
        int month = Integer.parseInt(src.substring(4,6));
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        //把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sd.format(a.getTime()));
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}

