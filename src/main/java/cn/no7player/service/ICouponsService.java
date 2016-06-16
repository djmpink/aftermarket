package cn.no7player.service;

import cn.no7player.model.Coupons;

/**
 * Created by zhouli on 2016/6/16.
 */
public interface ICouponsService {
    String getNewActivityCode();

    Coupons addCouponsBind(Coupons coupons);
}
