package com.zhangbao.gmall.mock.db.service;

import com.zhangbao.gmall.mock.db.bean.OrderDetailActivity;
import com.zhangbao.gmall.mock.db.bean.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 活动与订单关联表 服务类
 * </p>
 *
 * @author zhangchen
 * @since 2020-02-25
 */
public interface ActivityOrderService extends IService<OrderDetailActivity> {



    public List<OrderDetailActivity>  genActivityOrder(List<OrderInfo> orderInfoList, Boolean ifClear);

    public  void  saveActivityOrderList( List<OrderDetailActivity> activityOrderList);

}
