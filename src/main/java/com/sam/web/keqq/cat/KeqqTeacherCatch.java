package com.sam.web.keqq.cat;

import com.alibaba.fastjson.TypeReference;
import com.nicole.web.FastJSONHelper;
import com.nicole.web.HttpUtil;
import com.sam.web.keqq.model.LessonSource;
import com.sam.web.keqq.model.LessonItemList;
import com.sam.web.keqq.model.Result;
import org.apache.http.message.BasicHeader;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class KeqqTeacherCatch implements NetworkCatch<LessonSource> {

    private int aid;
    private String homepage;

    public KeqqTeacherCatch(int aid, String homepage) {
        this.aid = aid;
        this.homepage = homepage;
    }

    @Override
    public LessonSource getData() {
        String url = "https://" + homepage + "/cgi-bin/agency_new/get_teachers?count=1000&page=0&aid=" + aid;
        String html = HttpUtil.get(url,
                new BasicHeader("referer", "https://" + homepage));
        System.out.println(html);
        Result<LessonItemList> result = FastJSONHelper.deserializeAny(html,
                new TypeReference<Result<LessonItemList>>(){});
        if (result != null && result.getResult() != null && result.getResult().getItems() != null
                && result.getResult().getItems().size() > 0) {
            LessonSource ls = new LessonSource();
            ls.setTeacher_num(result.getResult().getItems().size());
            return ls;
        }
        return null;
    }
}
