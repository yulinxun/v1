package com.yu.api;

import com.sun.org.apache.regexp.internal.RE;
import com.yu.commons.pojo.ResultBean;

/**
 * @author yu
 * @date 2019/11/14 0014
 */
public interface ICartService {
    public ResultBean add(String token,Long productId,int count);
    public ResultBean del(String token,Long productId);
    public ResultBean update(String token,Long productId,int count);
    public ResultBean query(String token);
}
