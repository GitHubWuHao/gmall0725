package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface BaseAttrService {
    List<BaseAttrInfo> getAttrList(String catalog3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    List<BaseAttrInfo> attrInfoList(String catalog3Id);

    List<BaseAttrInfo> getAttrListByValueIds(Set<String> valueIds);

    List<BaseAttrValue> getAttrValueList(String attrId);
}
