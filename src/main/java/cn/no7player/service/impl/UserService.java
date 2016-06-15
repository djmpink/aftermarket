package cn.no7player.service.impl;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;
import cn.no7player.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2016/6/11.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        User user = userMapper.findUserInfo(userName);
        return user != null && user.getPassword().equals(password);
    }
}
