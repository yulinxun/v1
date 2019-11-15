package com.yu.vo;

import com.yu.entity.TProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yu
 * @date 2019/10/29 0029
 */
@Data
public class ProductVO implements Serializable{
    private TProduct product;
    private String productDesc;
}
