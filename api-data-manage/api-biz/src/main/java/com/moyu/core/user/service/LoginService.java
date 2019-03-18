package com.moyu.core.user.service;


import com.moyu.core.user.domain.MyUser;

/**
 *
 */
public interface LoginService {


    MyUser getUser(String loginName);
}
