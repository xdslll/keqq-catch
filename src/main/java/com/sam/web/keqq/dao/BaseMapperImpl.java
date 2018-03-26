package com.sam.web.keqq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public abstract class BaseMapperImpl<T> extends BaseMapper implements Mapper<T> {

    private String getMapper() {
        Class<?>[] interfaces = this.getClass().getInterfaces();
        if (interfaces.length > 0)
            return interfaces[0].getName();
        return null;
    }

    @Override
    public List<T> selectByParam(Map<String, Object> params) {
        return _list(getMapper() + ".selectByParam", params);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return _operator(getMapper() + ".deleteByPrimaryKey", id, DELETE);
    }

    @Override
    public int insert(T data) {
        return _operator(getMapper() + ".insert", data, INSERT);
    }

    @Override
    public int insertSelective(T data) {
        return _operator(getMapper() + ".insertSelective", data, INSERT);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        List<T> list = _list(getMapper() + ".selectByPrimaryKey", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(T data) {
        return _operator(getMapper() + ".updateByPrimaryKeySelective", data, UPDATE);
    }

    @Override
    public int updateByPrimaryKey(T data) {
        return _operator(getMapper() + ".updateByPrimaryKey", data, UPDATE);
    }

    protected T first(Map<String, Object> params) {
        List<T> list = selectByParam(params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<T> selectAll() {
        return _list(getMapper() + ".selectAll", null);
    }

    @Override
    public boolean exists(T data) {
        return false;
    }

    @Override
    public T selectByName(T data) {
        return null;
    }
}
