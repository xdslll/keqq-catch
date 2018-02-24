package com.sam.web.keqq.handler;

import com.sam.web.keqq.cat.KeqqSourceCatch;
import com.sam.web.keqq.cat.KeqqTeacherCatch;
import com.sam.web.keqq.dao.LessonMapper;
import com.sam.web.keqq.dao.LessonMapperImpl;
import com.sam.web.keqq.dao.LessonSourceMapper;
import com.sam.web.keqq.dao.LessonSourceMapperImpl;
import com.sam.web.keqq.model.Lesson;
import com.sam.web.keqq.model.LessonSource;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class KeqqLessonSourceHandler extends BaseHandler<LessonSourceMapper, LessonSource> {

    public void getData() throws UnsupportedEncodingException {
        //通过课程中的source字段获取所有教育机构的名称
        LessonMapper lessonMapper = new LessonMapperImpl();
        LessonSourceMapper lessonSourceMapper = new LessonSourceMapperImpl();
        List<Lesson> lessonList = lessonMapper.selectAllSource();
        int index = 1;
        for (Lesson lesson : lessonList) {
            System.out.println("正在获取第" + index + "条数据...");
            //通过教育机构的名称查询数据
            LessonSource lsInfo = new KeqqSourceCatch(lesson.getSource()).getData();
            if (lsInfo != null) {
                //获取教育机构下所有教师的数据
                LessonSource lsTeacher = new KeqqTeacherCatch(lsInfo.getAid(), lsInfo.getSite()).getData();
                if (lsTeacher != null) {
                    lsInfo.setTeacher_num(lsTeacher.getTeacher_num());
                }
                //将结果保存到数据库
                saveToDb(lessonSourceMapper, lsInfo);
            }
            index++;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        new KeqqLessonSourceHandler().getData();
    }

}
