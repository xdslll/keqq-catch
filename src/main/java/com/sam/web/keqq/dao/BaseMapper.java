package com.sam.web.keqq.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author xiads
 * @date 18/01/2018
 * @since
 */
public abstract class BaseMapper {

    private SqlSessionFactory factory = null;

    public static final String MAPPER_LESSON = "com.sam.web.keqq.dao.LessonMapper";
    public static final String MAPPER_LESSON_SOURCE = "com.sam.web.keqq.dao.LessonSourceMapper";

    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    protected BaseMapper() {
        try {
            String resource = "mybatis_conf.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> List<T> _list(String statement, Object obj) {
        SqlSession session = null;
        try {
            session = openSession();
            if (obj == null) {
                return session.selectList(statement);
            } else {
                return session.selectList(statement, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     *
     * @param statement
     * @param obj
     * @param code 0=insert,1=update,2=delete
     * @return
     */
    protected int _operator(String statement, Object obj, int code) {
        SqlSession session = null;
        try {
            session = openSession();
            switch (code) {
                case INSERT :
                    return session.insert(statement, obj);
                case UPDATE:
                    return session.update(statement, obj);
                case DELETE:
                    return session.delete(statement, obj);
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
        return 0;
    }

    protected SqlSession openSession() throws IOException {
        return factory.openSession();
    }

}
