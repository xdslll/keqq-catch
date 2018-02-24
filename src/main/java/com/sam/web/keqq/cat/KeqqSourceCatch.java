package com.sam.web.keqq.cat;

import com.alibaba.fastjson.TypeReference;
import com.nicole.web.FastJSONHelper;
import com.nicole.web.HttpUtil;
import com.sam.web.keqq.model.LessonSource;
import com.sam.web.keqq.model.LessonSourceList;
import com.sam.web.keqq.model.Result;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class KeqqSourceCatch implements NetworkCatch<LessonSource> {

    private String source;

    public KeqqSourceCatch(String source) {
        this.source = source;
    }

    @Override
    public LessonSource getData() throws UnsupportedEncodingException {
        String url = "https://ke.qq.com/cgi-bin/search/search_agent";
        String params = "page=1&count=10&keyword="+ URLEncoder.encode(source, "UTF-8");
        String html = HttpUtil.post(url, params,
                new BasicHeader("referer", "https://ke.qq.com/searchAgency.html?word=" + URLEncoder.encode(source, "UTF-8")),
                new BasicHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8"));
        System.out.println(html);
        Result<LessonSourceList> result = FastJSONHelper.deserializeAny(html, new TypeReference<Result<LessonSourceList>>(){});
        if (result != null && result.getResult() != null && result.getResult().getAlist() != null
                && result.getResult().getAlist().size() > 0) {
            LessonSource ls = result.getResult().getAlist().get(0);
            if (ls != null) {
                ls.setShort_name(source);
                System.out.println(ls.toString());
                return ls;
            }
        }
        return null;
    }
}
