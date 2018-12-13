package com.atguigu.gmall.user.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.mapper.UserAddressMapper;
import com.atguigu.gmall.mapper.UserInfoMapper;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    //UserInfo的增删改查
    @Override
    public int saveUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int removeUserInfo(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public List<UserInfo> getUserInfoList() {

        return userInfoMapper.selectAll();
    }
    @Override
    public UserInfo getByPrimaryKey(Integer id){

        return userInfoMapper.selectByPrimaryKey(id);
    }

    //UserAddress的增删改查
    @Override
    public int saveUserAddress(UserAddress userAddress) {
        return userAddressMapper.insert(userAddress);
    }

    @Override
    public int removeUserAddress(Integer id) {
        return userAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUserAddress(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Override
    public List<UserAddress> getUserAddressList() {
        return userAddressMapper.selectAll();
    }

    @Override
    public UserAddress getUserAddressById(Integer id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }
}
