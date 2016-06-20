package cn.no7player.service.impl;

import cn.no7player.common.bean.PageResult;
import cn.no7player.mapper.CouponsMapper;
import cn.no7player.model.Coupons;
import cn.no7player.pojo.CouponsReq;
import cn.no7player.service.ICouponsService;
import cn.no7player.utils.DateUtil;
import cn.no7player.utils.RandomTextUtils;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by zhouli on 2016/6/16.
 */

@Service
public class CouponsService implements ICouponsService {
    private static Logger logger = Logger.getLogger(CouponsService.class);

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
        String startDate = coupons.getStartTime();
        String endDate = coupons.getEndTime();
        int startDelta = DateUtil.getTimeDelta(startDate);
        int endDelta = DateUtil.getTimeDelta(endDate);

        if (endDelta > 0) {
            coupons.setStatus(6);//超期了
        }
        if (startDelta < 0) {
            coupons.setStatus(7);//未生效
        }
        return coupons;
    }

    @Override
    public PageResult<Coupons> getCouponsList(CouponsReq couponsReq) {
        int total = couponsMapper.getCouponsListByKeywordsTotal(couponsReq);
        List<Coupons> list = couponsMapper.getCouponsListByKeywords(couponsReq);
        for (Coupons coupons : list) {
            String startDate = coupons.getStartTime();
            String endDate = coupons.getEndTime();
            try {
                logger.info(DateUtil.parseDate(startDate).getTime());
                logger.info(DateUtil.parseDate(DateUtil.getNowTime()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int startDelta = DateUtil.getTimeDelta(startDate,"yyyy-MM-dd HH:mm:ss.SSS");
            int endDelta = DateUtil.getTimeDelta(endDate,"yyyy-MM-dd HH:mm:ss.SSS");
            logger.info(startDelta);
            logger.info(endDelta);
            if (endDelta > 0) {
                coupons.setStatus(6);//超期了
            }
            if (startDelta < 0) {
                coupons.setStatus(7);//未生效
            }
        }
        return new PageResult<>(list,total , couponsReq.getCurrentPage(), couponsReq.getPageSize());
    }
}
