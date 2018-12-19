package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;
import com.atguigu.gmall.service.BaseAttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BaseAttrController {

    @Reference
    private BaseAttrService baseAttrService;

    /**
     * 编辑属性值--通过商品属性id获取商品属性值列表
     * @param attrId
     * @return
     */
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId){
        List<BaseAttrValue> baseAttrValues = baseAttrService.getAttrValueList(attrId);
        return baseAttrValues;
    }

    /**
     * spu管理--增加sku--获取平台属性和属性值
     * @param catalog3Id
     * @return
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){

        List<BaseAttrInfo> baseAttrInfos = baseAttrService.attrInfoList(catalog3Id);

        return baseAttrInfos;
    }

    /**
     * 跳转属性列表页面
     * @return
     */
    @RequestMapping("attrListPage")
    public String attrListPage(){
        return "attrListPage";
    }

    /**
     * 刷新列表--获取属性列表
     * @param catalog3Id
     * @return
     */
    @RequestMapping("getAttrList")
    @ResponseBody
    public List<BaseAttrInfo> getAttrList(String catalog3Id){
        List<BaseAttrInfo> baseAttrInfos = baseAttrService.getAttrList(catalog3Id);
        return baseAttrInfos;
    }

    /**
     * 保存或更新平台属性信息
     * @param baseAttrInfo
     * @return
     */
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        baseAttrService.saveAttrInfo(baseAttrInfo);
        return "SUCCESS";
    }
}
