package com.zhangbao.gmall.mock.db.service;

import com.zhangbao.gmall.mock.db.bean.FavorInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品收藏表 服务类
 * </p>
 *
 * @author zhangchen
 * @since 2020-02-24
 */
public interface FavorInfoService extends IService<FavorInfo> {

    public void  genFavors(Boolean ifClear);

}
