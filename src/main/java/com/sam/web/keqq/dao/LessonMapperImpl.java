package com.sam.web.keqq.dao;

import com.sam.web.keqq.model.Lesson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiads
 * @date 18/01/2018
 * @since
 */
public class LessonMapperImpl extends BaseMapper implements LessonMapper {

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return _operator(MAPPER_LESSON + ".deleteByPrimaryKey", id, DELETE);
    }

    @Override
    public int insert(Lesson record) {
        return _operator(MAPPER_LESSON + ".insert", record, INSERT);
    }

    @Override
    public int insertSelective(Lesson record) {
        return _operator(MAPPER_LESSON + ".insertSelective", record, INSERT);
    }

    @Override
    public Lesson selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Lesson record) {
        return _operator(MAPPER_LESSON + ".updateByPrimaryKeySelective", record, UPDATE);
    }

    @Override
    public int updateByPrimaryKey(Lesson record) {
        return 0;
    }

    @Override
    public List<Lesson> selectAllSource() {
        return _list(MAPPER_LESSON + ".selectAllSource", null);
    }

    @Override
    public List<Lesson> selectByParam(Map<String, Object> params) {
        return _list(MAPPER_LESSON + ".selectByParam", params);
    }

    @Override
    public Lesson selectByName(Lesson lesson) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("cid", String.valueOf(lesson.getCid()));
        return selectByParam(param).get(0);
    }

    @Override
    public boolean exists(Lesson lesson) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("cid", String.valueOf(lesson.getCid()));
        return selectByParam(param).size() > 0;
    }
}
