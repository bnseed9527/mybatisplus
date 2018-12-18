package com.wise.validation;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description TODO
 * @Author YsCY丶
 * @Date 2018/11/12 13:57
 * @Version 1.0
 **/
@Data
public class Response<T> implements Serializable {

    /**
     * 返回结果集
     */
    private T result;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 响应码
     */
    private Integer code;
    //set get 略


}
