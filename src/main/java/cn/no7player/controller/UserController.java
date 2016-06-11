package cn.no7player.controller;

import cn.no7player.service.IUserService;
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
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "login")
    @ResponseBody
    public boolean login(@RequestParam String userName,@RequestParam String password) {
        System.out.println("userName:"+userName);
        Boolean result = userService.login(userName,password);
        return result;
    }
}
