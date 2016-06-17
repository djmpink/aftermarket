package cn.no7player.pojo;

import cn.no7player.common.bean.QueryBean;

/**
 * Created by zhouli on 2016/6/17.
 */
public class CouponsReq extends QueryBean{
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
