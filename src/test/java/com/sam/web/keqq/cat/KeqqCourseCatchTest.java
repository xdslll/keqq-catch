package com.sam.web.keqq.cat;

import com.sam.web.keqq.model.Lesson;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author xiads
 * @date 22/01/2018
 * @since
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KeqqCourseCatchTest {

    @Test
    public void test1KeqqCourseCatch() {
        System.out.println("---------------------------");
        System.out.println("|       测试获取课程接口     |");
        System.out.println("---------------------------");
        try {
            int aid = 31542;
            String homepage = "youtiyun.ke.qq.com";
            List<Lesson> lessonList = new KeqqCourseCatch(aid, homepage).getData();
            for (Lesson lesson : lessonList) {
                Assert.assertTrue(lesson != null);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
