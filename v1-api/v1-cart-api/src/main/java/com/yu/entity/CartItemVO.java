package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yu
 * @date 2019/11/14 0014
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVO implements Serializable{
    private TProduct product;
    private int count;
    private Date updateTime;
}
