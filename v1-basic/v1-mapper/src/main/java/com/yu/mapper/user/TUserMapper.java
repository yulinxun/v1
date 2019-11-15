package com.yu.mapper.user;

import com.yu.commons.base.IBaseDao;
import com.yu.commons.pojo.ResultBean;
import com.yu.entity.TUser;

public interface TUserMapper extends IBaseDao<TUser>{
    
    int checkUserNameIsExists(String username);
    int checkEmailIsExists(String Email);
    int checkPhoneIsExists(String Phone);

    TUser selectUserByIdentification(String username);
}