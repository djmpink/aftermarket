package cn.no7player.service.impl;

import cn.no7player.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2016/6/11.
 */
@Service
public class UserService implements IUserService {
    @Override
    public boolean login(String userName, String password) {
        return true;
    }
}
