package com.sam.web.keqq.dao;

import com.sam.web.keqq.model.LessonSource;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LessonSourceImplTest {

    private LessonSource lessonSource = new LessonSource();
    private LessonSourceMapper mapper = new LessonSourceMapperImpl();

    @Before
    public void prepareTestData() {
        System.out.println("---------------------------");
        System.out.println("|        准备测试数据        |");
        System.out.println("---------------------------");
        lessonSource.setShort_name("简称");
        lessonSource.setName("全称");
        lessonSource.setOwner(222);
        lessonSource.setSite("http://www.baidu.com");
        lessonSource.setTeacher_num(10);
        lessonSource.setTotal_signed(10002);
        lessonSource.setService_qq(123456);
        lessonSource.setAid(333);

        if (!mapper.exists(lessonSource)) {
            System.out.println("[准备] 测试数据不存在，开始插入...");
            mapper.insertSelective(lessonSource);
        }

        System.out.println("[准备] 开始获取测试数据ID...");
        lessonSource = mapper.selectByName(lessonSource);
        System.out.println("[准备] " + lessonSource.toString());
    }

    @Test
    public void test1() {
        System.out.println("---------------------------");
        System.out.println("|        测试查询数据        |");
        System.out.println("---------------------------");
        LessonSource ls = mapper.selectByPrimaryKey(lessonSource.getId());
        Assert.assertTrue(ls != null);
        Assert.assertEquals(ls.getShort_name(), "简称");
        Assert.assertEquals(ls.getName(), "全称");
        Assert.assertEquals(ls.getOwner(), new Integer(222));
        Assert.assertEquals(ls.getSite(), "http://www.baidu.com");
        Assert.assertEquals(ls.getTeacher_num(), new Integer(10));
        Assert.assertEquals(ls.getTotal_signed(), new Integer(10002));
        Assert.assertEquals(ls.getService_qq(), new Integer(123456));
        Assert.assertEquals(ls.getAid(), new Integer(333));
    }

    @Test
    public void test2() {
        System.out.println("---------------------------");
        System.out.println("|        测试更新数据        |");
        System.out.println("---------------------------");
        lessonSource.setShort_name("简称1");
        lessonSource.setName("全称1");
        lessonSource.setOwner(333);
        lessonSource.setSite("http://www.qq.com");
        lessonSource.setTeacher_num(15);
        lessonSource.setTotal_signed(10003);
        lessonSource.setService_qq(1234567);
        lessonSource.setAid(444);
        mapper.updateByPrimaryKeySelective(lessonSource);

        LessonSource ls = mapper.selectByPrimaryKey(lessonSource.getId());
        Assert.assertTrue(ls != null);
        Assert.assertEquals(ls.getShort_name(), "简称1");
        Assert.assertEquals(ls.getName(), "全称1");
        Assert.assertEquals(ls.getOwner(), new Integer(333));
        Assert.assertEquals(ls.getSite(), "http://www.qq.com");
        Assert.assertEquals(ls.getTeacher_num(), new Integer(15));
        Assert.assertEquals(ls.getTotal_signed(), new Integer(10003));
        Assert.assertEquals(ls.getService_qq(), new Integer(1234567));
        Assert.assertEquals(ls.getAid(), new Integer(444));
    }

    @After
    public void deleteTestData() {
        System.out.println("---------------------------");
        System.out.println("|        删除测试数据        |");
        System.out.println("---------------------------");
        if (mapper.exists(lessonSource)) {
            System.out.println("[结束] 测试数据存在，开始删除...");
            mapper.deleteByPrimaryKey(lessonSource.getId());

            LessonSource ls = mapper.selectByPrimaryKey(lessonSource.getId());
            Assert.assertTrue(ls == null);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/keqq",
                    "root",
                    "123456"
            );
            PreparedStatement ps = conn.prepareStatement("ALTER TABLE lesson_source auto_increment=1");
            ps.execute();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
