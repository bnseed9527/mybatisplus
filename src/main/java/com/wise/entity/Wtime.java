package com.wise.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Wtime
 * @Description 班次表
 * @Author YsCY丶
 * @Date 2018/12/7 13:22
 * @Version 1.0
 **/
@Data
public class Wtime {

    private int id;
    /**
     * 班次名称
     */
    private String name;

    /**
     * 开始日期
     */
    private Date start_time;
    /**
     * 周期数
     */
    private int cycle;

    public Wtime(int id, String name,Date start_time, int cycle) {
        this.id = id;
        this.name = name;
        this.start_time = start_time;
        this.cycle = cycle;
    }
    public Wtime(){}
}
