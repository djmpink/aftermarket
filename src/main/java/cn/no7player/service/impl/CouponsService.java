package cn.no7player.service.impl;

import cn.no7player.service.ICouponsService;
import cn.no7player.utils.RandomTextUtils;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2016/6/16.
 */

@Service
public class CouponsService implements ICouponsService {
    @Override
    public String getNewActivityCode() {
        return RandomTextUtils.createActivityCode(8);
    }
}
