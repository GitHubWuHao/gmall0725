package com.atguigu.gmall.user.service.imp;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.mapper.UserAddressMapper;
import com.atguigu.gmall.mapper.UserInfoMapper;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.util.GmallConst;
import com.atguigu.gmall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private RedisUtil redisUtil;

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

    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfo userInfo1 = userInfoMapper.selectOne(userInfo);
        if (userInfo1 != null){
            //获取一个jsdis
            Jedis jedis = redisUtil.getJedis();

            //通过key删除缓存中原数据
            jedis.del(GmallConst.CACHE_KEY_PREFIX_USER+userInfo1.getId()+GmallConst.CACHE_KEY_SUFFIX_INFO);

            Map<String, String> map = new HashMap<>();

            map.put(userInfo1.getLoginName(), JSON.toJSONString(userInfo1));

            //放入缓存
            jedis.hmset(GmallConst.CACHE_KEY_PREFIX_USER+userInfo1.getId()+GmallConst.CACHE_KEY_SUFFIX_INFO,map);
            //关闭资源
            jedis.close();
        }
        return userInfo1;

    }

    //通过userId获取用户地址
    @Override
    public List<UserAddress> getAddressListByUserId(String userId) {

        UserAddress userAddress =  new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> userAddresses = userAddressMapper.select(userAddress);

        return userAddresses;
    }

    @Override
    public UserAddress getAddressListById(String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        UserAddress userAddressDb = userAddressMapper.selectOne(userAddress);

        return userAddressDb;
    }
}
