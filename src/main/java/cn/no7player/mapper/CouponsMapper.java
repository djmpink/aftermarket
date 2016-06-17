package cn.no7player.mapper;

import cn.no7player.model.Coupons;
import cn.no7player.pojo.CouponsReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhouli on 2016/6/16.
 */
public interface CouponsMapper {

    int addCouponsBind(Coupons coupons);
    void editCoupons(Coupons coupons);
    Coupons getCouponsByActivityCode(@Param("activityCode") String activityCode,@Param("phone") String phone);

    List<Coupons> getCouponsListByKeywords(CouponsReq couponsReq);

    Integer getCouponsListByKeywordsTotal(CouponsReq couponsReq);
}
