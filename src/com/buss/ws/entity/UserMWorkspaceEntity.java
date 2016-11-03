package com.buss.ws.entity;

import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.util.List;

/**
 * Created by LuQP on 2016/9/6.
 */
public class UserMWorkspaceEntity {
    private List<MWorkspaceEntity> mwList;
    private String userName;

    public List<MWorkspaceEntity> getMwList() {
        return mwList;
    }

    public void setMwList(List<MWorkspaceEntity> mwList) {
        this.mwList = mwList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
