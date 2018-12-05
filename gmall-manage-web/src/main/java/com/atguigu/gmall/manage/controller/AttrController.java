package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttrController {

    @Reference
    private AttrService attrService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){

        List<BaseAttrInfo> baseAttrInfos = attrService.attrInfoList(catalog3Id);

        return baseAttrInfos;
    }

    @RequestMapping("attrListPage")
    public String attrListPage(){
        return "attrListPage";
    }

    //获取属性列表
    @RequestMapping("getAttrList")
    @ResponseBody
    public List<BaseAttrInfo> getAttrList(String catalog3Id){
        List<BaseAttrInfo> baseAttrInfos = attrService.getAttrList(catalog3Id);
        return baseAttrInfos;
    }

    //保存平台属性信息
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        attrService.saveAttrInfo(baseAttrInfo);
        return "SUCCESS";
    }
}
