package com.sam.web.mizhu.handler;

import com.sam.web.mizhu.dao.ImageMapper;
import com.sam.web.mizhu.dao.ImageMapperImpl;
import com.sam.web.mizhu.model.Good;
import com.sam.web.mizhu.model.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiads
 * @date 07/03/2018
 * @since
 */
public class ImageHandler {

    private ImageMapper mapper = new ImageMapperImpl();

    public List<Image> getSlideImageFromGood(String goodNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("relatedId", goodNo);
        param.put("style", 6);
        return mapper.selectByParam(param);
    }

}
