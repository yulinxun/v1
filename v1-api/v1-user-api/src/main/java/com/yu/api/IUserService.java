package com.yu.api;

import com.yu.commons.base.IBaseService;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TUser;

/**
 * @author yu
 * @date 2019/11/11 0011
 */
public interface IUserService extends IBaseService<TUser> {
    public ResultBean checkUserNameIsExists(String username);
    public ResultBean checkPhoneIsExists(String phone);
    public ResultBean checkEmailIsExists(String email);

    ResultBean checkLogin(TUser user);

    ResultBean checkIsLogin(String uuid);
}
