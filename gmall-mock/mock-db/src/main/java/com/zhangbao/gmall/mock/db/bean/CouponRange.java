package com.zhangbao.gmall.mock.db.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class CouponRange {

    Long id;

    Long couponId;

    String rangeType;

    Long rangeId;



}
