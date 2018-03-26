package com.sam.web.mizhu.handler;

import com.sam.web.mizhu.dao.GoodMapper;
import com.sam.web.mizhu.dao.GoodMapperImpl;
import com.sam.web.mizhu.model.Good;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author xiads
 * @date 07/03/2018
 * @since
 */
public class GoodHandler {

    GoodMapper goodMapper = new GoodMapperImpl();

    public List<Good> getGoodList() {
        return goodMapper.selectAll();
    }

    public Good getGoodById(int id) {
        return goodMapper.selectByPrimaryKey(id);
    }

    public String getImageUrl(Good good) {
        String imgUrlSection = good.getImageUrl();
        return getImageUrl(imgUrlSection);
    }

    public String getImageUrl(String imgUrlSection) {
        if (imgUrlSection == null || imgUrlSection.equals("")) {
            return null;
        }
        StringBuilder imageUrl = new StringBuilder();
        imageUrl.append("http://api.mizhu100.cn")
                .append(imgUrlSection);
        return imageUrl.toString();
    }

    public String[] getContentUrl(Good good) {
        String content = good.getContent();
        if (content == null || content.equals("")) {
            return null;
        }
        Document doc = Jsoup.parse(content);
        Elements imgs = doc.getElementsByTag("img");
        String[] src = new String[imgs.size()];
        for (int i = 0; i < src.length; i++) {
            src[i] = imgs.get(i).attr("src");
        }
        return src;
    }
}
