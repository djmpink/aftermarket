package cn.no7player.mapper;

import cn.no7player.model.Coupons;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhouli on 2016/6/16.
 */
public interface CouponsMapper {

    Coupons addCouponsBind(Coupons coupons);

    Coupons getCouponsByActivityCode(@Param("activityCode") String activityCode);
}
