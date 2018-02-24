package com.sam.web.keqq.cat;

import com.alibaba.fastjson.TypeReference;
import com.nicole.web.FastJSONHelper;
import com.nicole.web.HttpUtil;
import com.sam.web.keqq.dao.LessonMapper;
import com.sam.web.keqq.dao.LessonMapperImpl;
import com.sam.web.keqq.model.*;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiads
 * @date 18/01/2018
 * @since
 */
public class KeqqLessonCatch {

    public void catchData() {
        //生成数据库模型
        LessonMapper lessonMapper = new LessonMapperImpl();
        //课程列表的URL
        int page = 1;
        //IT课程总
        //String keListUrl = "https://ke.qq.com/course/list?mt=1001&page=" + page;
        int[] st = {
                2001,//互联网产品
                2010,//互联网营销
                2002,//编程语言
                2004,//前端开发
                2003,//移动开发
                2005,//网络与运维
                2008,//游戏开发
                2006,//软件研发
                2007,//云计算大数据
                2043,//硬件研发
                2009//认证考试
        };
        for (int i = 0; i < st.length; i++) {
            System.out.println("##########################################");
            System.out.println("#          正在读取课程类型 - " + st[i] + "         #");
            System.out.println("##########################################");
            parseAllUrl(page, st[i], lessonMapper);
            System.out.println("##########################################");
            System.out.println("#                读取完成                 #");
            System.out.println("##########################################");
        }
    }

    private void parseAllUrl(int page, int type, LessonMapper lessonMapper) {
        while (true) {
            String url = "https://ke.qq.com/course/list?mt=1001&st=" + type + "&page=" + page;
            if (parseUrl(url, page, type, lessonMapper)) {
                page++;
            } else {
                break;
            }
        }
    }

    private boolean parseUrl(String keListUrl, int page, int type, LessonMapper lessonMapper) {
        //获取列表内容
        String html = HttpUtil.get(keListUrl);
        //System.out.println(html);
        System.out.println("---------------------------");
        System.out.println("|     正在读取第" + page + "页          |");
        System.out.println("---------------------------");
        if (!parseHtml(html, type, lessonMapper)) {
            System.out.println("---------------------------");
            System.out.println("|         读取完成          |");
            System.out.println("---------------------------");
            return false;
        }
        return true;
    }

    private boolean parseHtml(String html, int type, LessonMapper lessonMapper) {
        //解析html
        Document doc = Jsoup.parse(html);
        Elements eleCourseCardList = doc.getElementsByClass("course-card-list").first().getElementsByClass("course-card-item");
        if (eleCourseCardList.size() <= 0)
            return false;
        for (Element courseCard : eleCourseCardList) {
            parseData(courseCard, type, lessonMapper);
            System.out.println("----------------------------------------------");
        }
        return true;
    }

    private void parseData(Element courseCard, int type, LessonMapper lessonMapper) {
        //获取标题
        String title = courseCard.getElementsByClass("item-tt").first().getElementsByTag("a").first().text();
        //获取详细页链接
        String url = courseCard.getElementsByClass("item-tt").first().getElementsByTag("a").first().attr("href");
        while (url.startsWith("/")) {
            url = url.substring(1, url.length());
        }
        //获取课程ID
        int lastSep = url.lastIndexOf("/");
        String cid = url.substring(lastSep + 1, url.length());
        //获取出品人或公司
        String source = courseCard.getElementsByClass("item-source").first().getElementsByTag("a").first().text();
        if (StringUtil.isBlank(source)) {
            source = courseCard.getElementsByClass("item-source-link").first().text();
        }
        //获取定价
        String sPrice = courseCard.getElementsByClass("item-price").first().text().trim();
        long dPrice = 0L;
        if (sPrice.equals("免费")) {
            dPrice = 0;
        } else {
            Pattern p = Pattern.compile("[0-9.]+");
            Matcher m = p.matcher(sPrice);
            if (m.find()) {
                dPrice = Math.round(Double.valueOf(m.group()));
            }
        }
        //将课程信息保存到数据库
        Lesson lesson = new Lesson();
        lesson.setCid(Integer.valueOf(cid));
        lesson.setTitle(title);
        lesson.setUrl(url);
        lesson.setSource(source);
        lesson.setPrice(dPrice);
        lesson.setType(type);
        System.out.println(lesson.toString());

        //获取评论和报名信息
        String detailUrl = "https://" + url;
        Header[] headers = new Header[1];
        headers[0] = new BasicHeader("referer", detailUrl);
        //获取课程的评论信息
        String commentTotalUrl = "https://ke.qq.com/cgi-bin/comment_new/course_comment_stat?cid=" + cid;
        String commentJson = HttpUtil.get(commentTotalUrl, headers);
        System.out.println(commentJson);
        Result<LessonComment> commentResult = FastJSONHelper.deserializeAny(commentJson, new TypeReference<Result<LessonComment>>(){});
        if (commentResult != null && commentResult.getRetcode() == 0) {
            LessonComment lessonComment = commentResult.getResult();
            if (lessonComment != null) {
                lesson.setCommentTotalNum(lessonComment.getAll_num());
                lesson.setCommentGoodNum(lessonComment.getGood_num());
                lesson.setCommentBadNum(lessonComment.getBad_num());
                lesson.setCommentMediumNum(lessonComment.getMedium_num());
                lesson.setCommentRateNum(lessonComment.getGood_percentage());
                System.out.println(lessonComment.toString());
            }
        }
        //获取课程的详细信息
        String courseTypeUrl = "https://ke.qq.com/cgi-bin/agency/course/relationship?from_course_ids=%5B" + cid + "%5D";
        String typeJson = HttpUtil.get(courseTypeUrl, headers);
        System.out.println(typeJson);
        Result<LessonRelationship> relationshipResult = FastJSONHelper.deserializeAny(typeJson, new TypeReference<Result<LessonRelationship>>(){});
        if (relationshipResult != null && relationshipResult.getRetcode() == 0) {
            LessonRelationship relationship = relationshipResult.getResult();
            if (relationship != null && relationship.getRelationships() != null && relationship.getRelationships().size() > 0) {
                LessonFrom lessonFrom = relationship.getRelationships().get(0).getFrom();
                if (lessonFrom != null) {
                    lesson.setPrice((long) lessonFrom.getPrice());
                    lesson.setRecentApplyNum(lessonFrom.getRecent_sign_num());
                    lesson.setTotalApplyNum(lessonFrom.getApply_num());
                    System.out.println(lessonFrom.toString());
                }
            } else {
                String courseDetailUrl = "https://ke.qq.com/cgi-bin/courseDetail_json?course_id=" + cid;
                String courseDetailJson = HttpUtil.get(courseDetailUrl);
                System.out.println(courseDetailJson);
                Result<LessonFrom> detailResult = FastJSONHelper.deserializeAny(typeJson, new TypeReference<Result<LessonFrom>>(){});
                if (detailResult != null && detailResult.getRetcode() == 0 && detailResult.getResult() != null) {
                    LessonFrom lessonFrom = detailResult.getResult();
                    lesson.setTotalApplyNum(lessonFrom.getApply_num());
                    lesson.setCommentRateNum(lessonFrom.getCourse_good_percentage());
                    System.out.println(lessonFrom.toString());
                }
            }
        }
        saveToDb(lesson, lessonMapper);
    }

    private void saveToDb(Lesson lesson, LessonMapper lessonMapper) {
        //判断数据是否存在
        if (lessonMapper.exists(lesson)) {
            System.out.println("[更新] 数据存在，开始更新...");
            //获取id
            lesson.setId(lessonMapper.selectByName(lesson).getId());
            //更新数据
            lessonMapper.updateByPrimaryKeySelective(lesson);
            System.out.println("[更新] 更新(id=" + lesson.getId() + ")成功...");
        } else {
            System.out.println("[插入] 数据不存在，开始插入...");
            //插入数据库
            lessonMapper.insertSelective(lesson);
            System.out.println("[插入] 插入成功...");
        }
    }

    private void catchDetail() {
        String detailUrl = "https://ke.qq.com/course/149432";
        String commentTotalUrl = "https://ke.qq.com/cgi-bin/comment_new/course_comment_stat?cid=149432";
        String courseTypeUrl = "https://ke.qq.com/cgi-bin/agency/course/relationship?from_course_ids=%5B149432%5D";
        String commentListUrl = "https://ke.qq.com/cgi-bin/comment_new/course_comment_list?cid=149432&count=10&page=0&filter_rating=0";
        String courseDetailUrl = "https://ke.qq.com/cgi-bin/courseDetail_json?course_id=149432";
    }

    public static void main(String[] args) throws IOException {
        new KeqqLessonCatch().catchData();
        //new KeqqCatch().catchDetail();
    }



}
