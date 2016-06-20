package cn.no7player.service.impl;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;
import cn.no7player.service.IUserService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2016/6/11.
 */
@Service
public class UserService implements IUserService {

    private static Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        User user = userMapper.findUserInfo(userName);
        logger.info(JSON.toJSONString(user));
        return user != null && user.getPassword().equals(password);
    }
}
