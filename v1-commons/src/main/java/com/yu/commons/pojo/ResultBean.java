package com.yu.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yu
 * @date 2019/11/1 0001
 */
@Data
@AllArgsConstructor
public class ResultBean<T> implements Serializable{
    private String statusCode;
    private T data;
}
