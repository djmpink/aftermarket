package cn.no7player.controller;

import cn.no7player.common.ACK;
import cn.no7player.common.BaseController;
import cn.no7player.common.bean.Result;
import cn.no7player.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhouli on 2016/6/11.
 */
@Controller
@RequestMapping(value="user")
public class UserController extends BaseController{
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "login")
    @ResponseBody
    public Result login(@RequestParam String userName, @RequestParam String password) {
        System.out.println("userName:"+userName);
        Boolean flag = userService.login(userName,password);
        logger.info("flag:"+flag);
        if (flag){
            return resultOK(flag);
        }else {
            return resultError(ACK.USER_NOT_AUTH,"用户不存在或密码错误");
        }
    }
}
