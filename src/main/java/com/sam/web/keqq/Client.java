package com.sam.web.keqq;

import com.sam.web.keqq.handler.KeqqLessonCourseHandler;
import com.sam.web.keqq.handler.KeqqLessonSourceHandler;

import java.io.UnsupportedEncodingException;

/**
 * @author xiads
 * @date 24/02/2018
 * @since
 */
public class Client {

    public static void main(String[] args) throws UnsupportedEncodingException {
        new KeqqLessonSourceHandler().getData();
        new KeqqLessonCourseHandler().getData();
    }

}
