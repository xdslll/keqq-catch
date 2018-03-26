package com.sam.web.mizhu.handler;

import com.sam.web.mizhu.model.Good;
import com.sam.web.mizhu.model.Image;
import org.junit.Test;

import java.util.List;

/**
 * @author xiads
 * @date 07/03/2018
 * @since
 */
public class ImageHandlerTest {

    @Test
    public void testImageHandlerByGood() {
        ImageHandler handler = new ImageHandler();
        printImageFromGood(handler, "200010");
        printImageFromGood(handler, "200011");
        printImageFromGood(handler, "200012");
    }

    private void printImageFromGood(ImageHandler handler, String goodNo) {
        System.out.println(goodNo + ":");
        List<Image> image1 = handler.getSlideImageFromGood(goodNo);
        for (Image img : image1) {
            System.out.println(img.toString());
        }
    }

}
