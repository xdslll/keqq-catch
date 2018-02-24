package com.sam.web.keqq.cat;

import com.sam.web.keqq.model.LessonSource;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.UnsupportedEncodingException;

/**
 * @author xiads
 * @date 22/01/2018
 * @since
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KeqqLessonCatchTest {

    @Test
    public void test1KeqqSourceCatch() {
        System.out.println("---------------------------");
        System.out.println("|     测试获取教育机构接口    |");
        System.out.println("---------------------------");
        try {
            String source = "51CTO学院";
            LessonSource ls = new KeqqSourceCatch(source).getData();
            Assert.assertTrue(ls != null);
            Assert.assertEquals(ls.getShort_name(), source);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2KeqqTeacherCatch() {
        System.out.println("---------------------------");
        System.out.println("|     测试获取教师信息接口    |");
        System.out.println("---------------------------");
        int aid = 10137;
        String homepage = "51edu.ke.qq.com";
        LessonSource ls = new KeqqTeacherCatch(aid, homepage).getData();
        Assert.assertTrue(ls != null);
        Assert.assertTrue(ls.getTeacher_num() > 0);
    }

}
