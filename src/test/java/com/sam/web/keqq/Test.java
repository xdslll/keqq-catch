package com.sam.web.keqq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiads
 * @date 24/01/2018
 * @since
 */
public class Test {

    @org.junit.Test
    public void timeTest() {
        long start = 1499823663 * 1000L;
        System.out.println(SimpleDateFormat.getInstance().format(new Date(start)));

        long end = 1551283200 * 1000L;
        System.out.println(SimpleDateFormat.getInstance().format(new Date(end)));


    }

}
