package com.zhangbao.gmall.mock.db.service;

import com.zhangbao.gmall.mock.db.bean.OrderInfo;
import com.zhangbao.gmall.mock.db.bean.OrderStatusLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2020-02-24
 */
public interface OrderStatusLogService extends IService<OrderStatusLog> {
    public void  genOrderStatusLog(List<OrderInfo> orderInfoList);

}
