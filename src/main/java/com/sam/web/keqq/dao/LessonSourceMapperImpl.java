package com.sam.web.keqq.dao;

import com.sam.web.keqq.model.LessonSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class LessonSourceMapperImpl extends BaseMapperImpl<LessonSource> implements LessonSourceMapper {

    @Override
    public boolean exists(LessonSource data) {
        Map<String, Object> params = new HashMap<>();
        params.put("short_name", data.getShort_name());
        return selectByParam(params).size() > 0;
    }

    @Override
    public LessonSource selectByName(LessonSource data) {
        Map<String, Object> params = new HashMap<>();
        params.put("short_name", data.getShort_name());
        return first(params);
    }
}
