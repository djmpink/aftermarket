package cn.no7player.service;

import cn.no7player.common.bean.PageResult;
import cn.no7player.model.Coupons;
import cn.no7player.pojo.CouponsReq;

/**
 * Created by zhouli on 2016/6/16.
 */
public interface ICouponsService {
    String getNewActivityCode();

    Coupons addCouponsBind(Coupons coupons);
    void editCoupons(Coupons coupons);

    Coupons checkCoupons(String activityCode,String phone);

    PageResult<Coupons> getCouponsList(CouponsReq couponsReq);
}
