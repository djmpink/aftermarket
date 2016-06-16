package cn.no7player.controller;

import cn.no7player.common.ACK;
import cn.no7player.common.BaseController;
import cn.no7player.common.bean.Result;
import cn.no7player.service.ICouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhouli on 2016/6/16.
 */
@Controller
@RequestMapping(value = "coupons")
public class CouponsController extends BaseController {
    @Autowired
    private ICouponsService couponsService;

    @RequestMapping(value = "getNewActivityCode",method = {RequestMethod.POST})
    @ResponseBody
    public Result getNewActivityCode() {
        String activityCode = couponsService.getNewActivityCode();
        if (activityCode != null && !activityCode.equals("")) {
            return resultOK(activityCode);
        } else {
            return resultError(ACK.USER_NOT_AUTH, "生成验证码异常");
        }
    }


    @RequestMapping(value = "addCouponsBind",method = {RequestMethod.POST})
    @ResponseBody
    public Result addCouponsBind() {
        String activityCode = couponsService.getNewActivityCode();
        if (activityCode != null && !activityCode.equals("")) {
            return resultOK(activityCode);
        } else {
            return resultError(ACK.USER_NOT_AUTH, "生成验证码异常");
        }
    }
}
