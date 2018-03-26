package com.sam.web.keqq.cat;

import com.alibaba.fastjson.TypeReference;
import com.nicole.web.FastJSONHelper;
import com.nicole.web.HttpUtil;
import com.sam.web.keqq.dao.LessonMapper;
import com.sam.web.keqq.dao.LessonSourceMapper;
import com.sam.web.keqq.dao.LessonSourceMapperImpl;
import com.sam.web.keqq.model.Lesson;
import com.sam.web.keqq.model.LessonItemList;
import com.sam.web.keqq.model.LessonSource;
import com.sam.web.keqq.model.Result;
import org.apache.http.message.BasicHeader;
import org.jsoup.helper.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiads
 * @date 24/01/2018
 * @since
 */
public class KeqqCourseCatch implements NetworkCatch<List<Lesson>> {

    private int aid;
    private String homepage;

    public KeqqCourseCatch(int aid, String homepage) {
        this.aid = aid;
        this.homepage = homepage;
    }

    @Override
    public List<Lesson> getData() throws UnsupportedEncodingException {
        String url = "https://" + homepage + "/cgi-bin/agency_new/get_courses?count=1000&page=0&category=-1&aid=" + aid;
        String html = HttpUtil.get(url,
                new BasicHeader("referer", "https://" + homepage));
        System.out.println(html);
        Result<LessonItemList<Lesson>> result = FastJSONHelper.deserializeAny(html,
                new TypeReference<Result<LessonItemList<Lesson>>>(){});
        if (result != null && result.getResult() != null
                && result.getResult().getItems() != null
                && result.getResult().getItems().size() > 0) {
            List<Lesson> lessonList = result.getResult().getItems();
            if (lessonList != null) {
                for (Lesson lesson : lessonList) {
                    lesson.setId(null);
                    if (!StringUtil.isBlank(lesson.getName())) {
                        lesson.setTitle(lesson.getName());
                    }
                    lesson.setTotalApplyNum(lesson.getApply_num());
                    lesson.setUrl("ke.qq.com/course/" + lesson.getCid());
                    //设置source
                    LessonSourceMapper lsm = new LessonSourceMapperImpl();
                    Map<String, Object> par = new HashMap<>();
                    par.put("aid", String.valueOf(lesson.getAid()));
                    List<LessonSource> lsList = lsm.selectByParam(par);
                    if (lsList != null && lsList.size() > 0) {
                        lesson.setSource(lsList.get(0).getShort_name());
                    }
                    lesson.setType(lesson.getIndustry2nd());
                    System.out.println(lesson.toString());
                }
            }
            return lessonList;
        }
        return null;
    }
}
