package com.sam.web.keqq.handler;

import com.sam.web.keqq.cat.KeqqCourseCatch;
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
public class KeqqLessonCourseHandler extends BaseHandler<LessonMapper, Lesson> {

    public void getData() throws UnsupportedEncodingException {
        //通过课程中的source字段获取所有教育机构的名称
        LessonMapper lessonMapper = new LessonMapperImpl();
        LessonSourceMapper lessonSourceMapper = new LessonSourceMapperImpl();
        List<LessonSource> sourceList = lessonSourceMapper.selectByParam(null);
        int index = 1;
        for (LessonSource source : sourceList) {
            System.out.println("正在获取第" + index + "条数据...");
            KeqqCourseCatch kcc = new KeqqCourseCatch(source.getAid(), source.getSite());
            List<Lesson> lessonList = kcc.getData();
            if (lessonList != null) {
                for (Lesson lesson : lessonList) {
                    if (lesson != null) {
                        saveToDb(lessonMapper, lesson);
                    } else {
                        System.out.println("课程数据不存在！");
                    }
                }
            }
            index++;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        new KeqqLessonCourseHandler().getData();
    }

}
