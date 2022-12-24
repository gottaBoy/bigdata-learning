package com.my.bigdata.ct.web.service;

import com.my.bigdata.ct.web.bean.Calllog;

import java.util.List;

public interface CalllogService {
    List<Calllog> queryMonthDatas(String tel, String calltime);
}
