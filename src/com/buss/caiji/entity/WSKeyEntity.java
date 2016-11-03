package com.buss.caiji.entity;

import com.buss.keyword.entity.MKeywordEntity;
import com.buss.ws.entity.MWorkspaceEntity;

import java.util.List;

/**
 * Created by LuQP on 2016/7/28.
 */
public class WSKeyEntity {
    private Integer id;
    private String wsName;
    private List<MKeywordEntity> keyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public List<MKeywordEntity> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<MKeywordEntity> keyList) {
        this.keyList = keyList;
    }
}
