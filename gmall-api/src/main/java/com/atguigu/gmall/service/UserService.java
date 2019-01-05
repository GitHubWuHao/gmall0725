package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;

import java.util.List;

public interface UserService {

    int saveUserInfo(UserInfo userInfo);

    int removeUserInfo(Integer id);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> getUserInfoList();

    UserInfo getByPrimaryKey(Integer id);

    int saveUserAddress(UserAddress userAddress);

    int removeUserAddress(Integer id);

    int updateUserAddress(UserAddress userAddress);

    List<UserAddress> getUserAddressList();

    UserAddress getUserAddressById(Integer id);

    UserInfo login(UserInfo userInfo);

    List<UserAddress> getAddressListByUserId(String userId);

    UserAddress getAddressListById(String addressId);

    void sendCartMerge(String userId,String cartListCookie);

    void sendCartFlushCache(String userId);
}
