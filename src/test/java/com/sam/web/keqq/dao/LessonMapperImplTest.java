package com.sam.web.keqq.dao;

import com.sam.web.keqq.model.Lesson;
import org.apache.http.util.Asserts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author xiads
 * @date 18/01/2018
 * @since
 */
public class LessonMapperImplTest {

    Lesson lesson = new Lesson();
    LessonMapper mapper = new LessonMapperImpl();

    @Before
    public void prepareTestData() {
        System.out.println("---------------------------");
        System.out.println("|        准备测试数据        |");
        System.out.println("---------------------------");
        lesson.setTitle("标题");
        lesson.setUrl("http://ke.qq.com");
        lesson.setPrice(100L);
        lesson.setSource("出品人");
        lesson.setCid(999999);
        if (!mapper.exists(lesson)) {
            System.out.println("[准备] 测试数据不存在，开始插入...");
            mapper.insertSelective(lesson);
        }
        System.out.println("[准备] 开始获取测试数据id...");
        lesson = mapper.selectByName(lesson);
        System.out.println("[准备] " + lesson.toString());

    }

    @Test
    public void testSelectData() {
        System.out.println("---------------------------");
        System.out.println("|        测试查询数据        |");
        System.out.println("---------------------------");
        mapper.selectByPrimaryKey(lesson.getId());
        Assert.assertTrue(lesson != null);
    }

    @After
    public void deleteTestData() {
        System.out.println("---------------------------");
        System.out.println("|        删除测试数据        |");
        System.out.println("---------------------------");
        if (mapper.exists(lesson)) {
            System.out.println("[结束] 测试数据存在，开始删除...");
            mapper.deleteByPrimaryKey(lesson.getId());

            Lesson l = mapper.selectByPrimaryKey(lesson.getId());
            Assert.assertTrue(l == null);
        }
    }
}
