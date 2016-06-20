package cn.no7player.controller;

import cn.no7player.common.ACK;
import cn.no7player.common.BaseController;
import cn.no7player.common.bean.PageResult;
import cn.no7player.common.bean.Result;
import cn.no7player.common.em.ResultCode;
import cn.no7player.model.Coupons;
import cn.no7player.pojo.CouponsReq;
import cn.no7player.service.ICouponsService;
import cn.no7player.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by zhouli on 2016/6/16.
 */
@Controller
@RequestMapping(value = "coupons")
public class CouponsController extends BaseController {
    private static Logger logger = Logger.getLogger(CouponsController.class);

    @Autowired
    private ICouponsService couponsService;

    @RequestMapping(value = "getNewActivityCode", method = {RequestMethod.POST})
    @ResponseBody
    public Result getNewActivityCode() {
        String activityCode = couponsService.getNewActivityCode();
        if (activityCode != null && !activityCode.equals("")) {
            return resultOK(activityCode);
        } else {
            return resultError(ACK.USER_NOT_AUTH, "生成验证码异常");
        }
    }


    @RequestMapping(value = "addCouponsBind", method = {RequestMethod.POST})
    @ResponseBody
    public Result addCouponsBind(Coupons coupons) {
        System.out.println(JSON.toJSONString(coupons));
        logger.info(JSON.toJSONString(coupons));
        if (coupons == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][coupons]");
        }
        if (coupons.getActivityCode() == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][activityCode]");
        }
        if (coupons.getPhone() == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][phone]");
        }
        couponsService.addCouponsBind(coupons);

        return resultOK();
    }

    @RequestMapping(value = "editCoupons", method = {RequestMethod.POST})
    @ResponseBody
    public Result editCoupons(Coupons coupons) {
        logger.info(JSON.toJSONString(coupons));
        if (coupons == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][coupons]");
        }
        if (coupons.getId() == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][id]");
        }
        couponsService.editCoupons(coupons);

        return resultOK();
    }


    @RequestMapping(value = "checkCoupons", method = {RequestMethod.POST})
    @ResponseBody
    public Result checkCoupons(String activityCode, String phone) {

        logger.info(activityCode);
        if (activityCode == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][activityCode]");
        }

        Coupons coupons = couponsService.checkCoupons(activityCode.toUpperCase(), phone);
        if (coupons == null) {
            return resultError(ACK.ACTIVITY_CODE_NOT_EXIST, "激活码不存在");
        }

        return resultOK(coupons);
    }

    @RequestMapping(value = "getCouponsList", method = {RequestMethod.POST})
    @ResponseBody
    public Result getCouponsList(CouponsReq couponsReq) {
        logger.info(JSON.toJSONString(couponsReq));
        if (couponsReq == null) {
            return resultError(ACK.PARAM_ERROR, "[参数异常][couponsReq]");
        }

        PageResult<Coupons> data = couponsService.getCouponsList(couponsReq);
        logger.info(JSON.toJSONString(data));
        return resultOK(data);
    }
}
