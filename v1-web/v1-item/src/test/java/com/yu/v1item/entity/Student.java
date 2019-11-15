package com.yu.v1item.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author yu
 * @date 2019/11/4 0004
 */
@Data
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Date date;
}


