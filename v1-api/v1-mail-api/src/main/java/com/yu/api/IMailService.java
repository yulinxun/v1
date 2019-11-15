package com.yu.api;

import com.yu.commons.pojo.ResultBean;

/**
 * @author yu
 * @date 2019/11/12 0012
 */
public interface IMailService {
    public ResultBean sendActivateMail(String to, String username);
}
