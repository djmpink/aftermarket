package cn.no7player.service.impl;

import cn.no7player.common.bean.PageResult;
import cn.no7player.mapper.CouponsMapper;
import cn.no7player.model.Coupons;
import cn.no7player.pojo.CouponsReq;
import cn.no7player.service.ICouponsService;
import cn.no7player.utils.DateUtil;
import cn.no7player.utils.RandomTextUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if (coupons.getPurchase()==null){
            coupons.setPurchase("0");
        }
        if (coupons.getStartTime()==null){
            coupons.setStartTime(DateUtil.getNowTime());
        }
        if (coupons.getEndTime()==null){
            coupons.setEndTime(DateUtil.getTime(DateUtil.getNowTime(),365));
        }
        coupons.setCreateTime(DateUtil.getNowTime());
        coupons.setEditTime(DateUtil.getNowTime());
        couponsMapper.addCouponsBind(coupons);
        return null;
    }

    @Override
    public void editCoupons(Coupons coupons) {
        if (coupons==null){
            return;
        }
        if (coupons.getId()==null){
            return;
        }

        if (coupons.getStatus()!=null){
            int status=coupons.getStatus();
            if (status==4){
                coupons.setUsedTime(DateUtil.getNowTime());
            }
            if (status==5){
                coupons.setCloseTime(DateUtil.getNowTime());
            }
        }
        coupons.setEditTime(DateUtil.getNowTime());
        couponsMapper.editCoupons(coupons);
    }

    @Override
    public Coupons checkCoupons(String activityCode,String phone) {
        Coupons coupons =couponsMapper.getCouponsByActivityCode(activityCode,phone);
        return coupons;
    }

    @Override
    public PageResult<Coupons> getCouponsList(CouponsReq couponsReq) {
        int total = couponsMapper.getCouponsListByKeywordsTotal(couponsReq);
        List<Coupons> list = couponsMapper.getCouponsListByKeywords(couponsReq);
        return new PageResult<>(list,total , couponsReq.getCurrentPage(), couponsReq.getPageSize());
    }
}
