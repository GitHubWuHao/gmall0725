package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;
import com.atguigu.gmall.manage.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService{

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return baseAttrInfoMapper.select(baseAttrInfo);
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        String id = baseAttrInfo.getId();
        //通过id是否未空判断是添加还是编辑
        if (StringUtils.isBlank(id)){
            //添加属性
            //存入平台属性信息
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
            //获取平台属性id
            String AttrId = baseAttrInfo.getId();
            //获取平台属性值列表
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            //遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //将属性和属性值关联
                baseAttrValue.setAttrId(AttrId);
                //保存入属性值表中
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }else {
            //编辑
        }
    }

    @Override
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        //查询平台属性
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.select(baseAttrInfo);
        //查询平台属性值
        for (BaseAttrInfo attrInfo : baseAttrInfos) {
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(attrInfo.getId());
            List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.select(baseAttrValue);

            attrInfo.setAttrValueList(baseAttrValues);
        }
        return baseAttrInfos;
    }
}
