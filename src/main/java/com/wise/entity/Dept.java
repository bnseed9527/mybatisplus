package com.wise.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName Dept
 * @Description TODO
 * @Author YsCY丶
 * @Date 2018/12/7 11:05
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dept {

    private int id;

    private String dname;

    private String dlocation;

    public Dept(int id, String dname, String dlocation) {
        this.id = id;
        this.dname = dname;
        this.dlocation = dlocation;
    }


    public Dept() {

    }


}
