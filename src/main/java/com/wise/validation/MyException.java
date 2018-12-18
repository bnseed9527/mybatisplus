package com.wise.validation;

import lombok.Data;

/**
 * @ClassName MyException
 * @Description TODO
 * @Author YsCY丶
 * @Date 2018/11/12 13:57
 * @Version 1.0
 **/
@Data
public class MyException extends RuntimeException {

    private static final long serialVersionUID = -5875371379845226068L;


    public MyException(){}

    public MyException(String msg){
        this.msg = msg ;
    }

    /**
     * 异常信息
     */
    private String msg ;

    /**
     * 具体异常码
     */
    private int code = Code.FAILED;
    //get set 略

}
