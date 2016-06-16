package cn.no7player.service.impl;

import cn.no7player.mapper.CouponsMapper;
import cn.no7player.model.Coupons;
import cn.no7player.service.ICouponsService;
import cn.no7player.utils.RandomTextUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2016/6/16.
 */

@Service
public class CouponsService implements ICouponsService {

    @Autowired
    private CouponsMapper couponsMapper;

    @Override
    public String getNewActivityCode() {
        return RandomTextUtils.createActivityCode(8);
    }

    @Override
    public Coupons addCouponsBind(Coupons coupons) {
        System.out.println(JSON.toJSONString(coupons));
        couponsMapper.addCouponsBind(coupons);
        return null;
    }
}
