package com.sam.web.mizhu.handler;

import com.sam.web.mizhu.model.Good;
import com.sam.web.mizhu.model.Image;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiads
 * @date 07/03/2018
 * @since
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoodHandlerTest {

    @Test
    public void test1GetGoodImage() throws IOException {
        GoodHandler goodHandler = new GoodHandler();
        ImageHandler imgHandler = new ImageHandler();
        //获取所有商品
        List<Good> goodList = goodHandler.getGoodList();
        int index = 1;
        //商品封面图片
        int imageUrlPass = 0;
        int imageUrlFailed = 0;
        ArrayList<String> failedImageUrlGood = new ArrayList<>();
        //商品详情页图片
        int contentUrlPass = 0;
        int contentUrlFailed = 0;
        ArrayList<String> failedContentUrlGood = new ArrayList<>();
        //容量小于200KB的详情页图片
        ArrayList<String> minContentUrlGood = new ArrayList<>();
        //商品轮播图
        int slideImgUrlPass = 0;
        int slideImgUrlFailed = 0;
        ArrayList<String> failedSlideImgUrlGood = new ArrayList<>();
        //轮播图数量大于4张的商品
        ArrayList<Good> countGtFourSlideImgUrlGood = new ArrayList<>();
        //上传重复的轮播图商品
        ArrayList<String> duplicatedSlideImgUrlGood = new ArrayList<>();
        //轮播图容量为0的商品
        ArrayList<Good> zeroSlideImgUrlGood = new ArrayList<>();

        //遍历商品
        for (Good good : goodList) {
            String imageUrl = goodHandler.getImageUrl(good);
            if (imageUrl == null || imageUrl.equals("")) {
                continue;
            }
            String[] contentUrls = goodHandler.getContentUrl(good);
            System.out.print(index + " : " + good.getId() + " : ");
            //测试商品封面图片是否存在
            if (testImage(imageUrl, "imageUrl")) {
                imageUrlPass++;
            } else {
                imageUrlFailed++;
                failedImageUrlGood.add(good.getGoodNo());
            }
            System.out.print(" : ");
            //测试商品详情页图片是否存在
            for (int i = 0; i < contentUrls.length; i++) {
                if (testImage(contentUrls[i], "contentUrl[" + ( i + 1 ) + "]")) {
                    contentUrlPass++;
                    //测试商品详情页是否小于200KB，如果小于则进行记录
                    if (testIsMinImage(contentUrls[i], 200 * 1024)) {
                        minContentUrlGood.add(good.getGoodNo());
                    }
                } else {
                    contentUrlFailed++;
                    failedContentUrlGood.add(good.getGoodNo());
                }
                if (contentUrls.length > 1) {
                    System.out.print(" / ");
                }
            }
            System.out.print(" : ");
            //测试商品轮播图片是否存在
            List<Image> imgList = imgHandler.getSlideImageFromGood(good.getGoodNo());
            //判断轮播图片是否大于4张
            if (imgList.size() > 4) {
                countGtFourSlideImgUrlGood.add(good);
            }
            for (int i = 0; i < imgList.size(); i++) {
                String imgUrl = goodHandler.getImageUrl(imgList.get(i).getImageUrl());
                //System.out.print(imgUrl + ":");
                if (testImage(imgUrl, "slideImgUrl[" + ( i + 1 ) + "]")) {
                    slideImgUrlPass++;
                    if (testIsZeroImage(imgUrl)) {
                        zeroSlideImgUrlGood.add(good);
                    }
                } else {
                    slideImgUrlFailed++;
                    failedSlideImgUrlGood.add(good.getGoodNo());
                }
                if (imgList.size() > 1 && i < imgList.size() - 1) {
                    System.out.print(" / ");
                }
            }
            //测试商品轮播图片中是否存在重复
            for (int i = 0; i < imgList.size(); i++) {
                String imgUrl = goodHandler.getImageUrl(imgList.get(i).getImageUrl());
                for (int j = i + 1; j < imgList.size(); j++) {
                    String imgUrl2 = goodHandler.getImageUrl(imgList.get(j).getImageUrl());
                    if (imgUrl.equals(imgUrl2)) {
                        duplicatedSlideImgUrlGood.add("[" + good.getGoodNo() + "]:" + imgUrl + " / " + imgUrl2);
                    }
                }
            }
            System.out.print("\n");
            index++;
        }
        System.out.println("[imageUrl] pass:" + imageUrlPass + ",failed:" + imageUrlFailed);
        System.out.println("[contentUrl] pass:" + contentUrlPass + ",failed:" + contentUrlFailed);
        System.out.println("[slideImgUrl] pass:" + slideImgUrlPass + ",failed:" + slideImgUrlFailed);
        System.out.println("[imageUrl] failed good : " + failedImageUrlGood.toString());
        System.out.println("[contentUrl] failed good : " + failedContentUrlGood.toString());
        System.out.println("[slideImgUrl] failed good : " + failedSlideImgUrlGood.toString());
        System.out.println("[contentUrl] image less than 200KB : " + minContentUrlGood.toString());
        System.out.println("[slideImgUrl] image less than 4 : ");
        for (Good good : countGtFourSlideImgUrlGood) {
            System.out.println(good);
        }
        System.out.println("[slideImgUrl] duplicated slide image : ");
        for (String duplicate : duplicatedSlideImgUrlGood) {
            System.out.println(duplicate);
        }
        System.out.println("[slideImgUrl] zero length img: ");
        for (Good good : zeroSlideImgUrlGood) {
            System.out.println(good);
        }

        Assert.assertTrue(imageUrlFailed == 0);
        Assert.assertTrue(contentUrlFailed == 0);
        Assert.assertTrue(slideImgUrlFailed == 0);
    }

    private boolean testIsMinImage(String contentUrl, int min) throws IOException {
        HttpResponse response = getImgResponse(contentUrl);
        long length = response.getEntity().getContentLength();
        return length < min;
    }

    private boolean testIsZeroImage(String contentUrl) throws IOException {
        HttpResponse response = getImgResponse(contentUrl);
        long length = response.getEntity().getContentLength();
        return length == 0;
    }

    private boolean testImage(String imageUrl, String tag) throws IOException {
        HttpResponse response = getImgResponse(imageUrl);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            long length = entity.getContentLength();
            System.out.print(tag + " => pass("+ showContentLength(length) + ")");
            return true;
        } else {
            System.out.print(tag + " => failed");
            return false;
        }
    }

    private HttpResponse getImgResponse(String imageUrl) throws IOException {
        HttpClient client = HttpClients.createDefault();
        return client.execute(new HttpGet(imageUrl));
    }

    //@Test
    public void testShowContentLength() {
        System.out.println(showContentLength(0));
        System.out.println(showContentLength(1000));
        System.out.println(showContentLength(2000));
        System.out.println(showContentLength(1024*1024));
        System.out.println(showContentLength(1024*1024*3));
    }

    private String showContentLength(long length) {
        if (length <= 0) {
            return "0";
        } else if (length > 0 && length < 1024) {
            return length + "B";
        } else if (length >= 1024 && length < 1024 * 1024) {
            return formatDouble(((double) length) / 1024) + "KB";
        } else if (length >= 1024 * 1024 && length < 1024 * 1024 * 1024) {
            return formatDouble(((double) length) / (1024 * 1024)) + "MB";
        } else if (length >= 1024 * 1024 * 1024 && length < 1024 * 1024 * 1024 * 1024) {
            return formatDouble(((double) length) / (1024 * 1024 * 1024)) + "GB";
        } else {
            return "0";
        }
    }

    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }
}
