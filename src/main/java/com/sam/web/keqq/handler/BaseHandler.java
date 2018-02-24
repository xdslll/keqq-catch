package com.sam.web.keqq.handler;

import com.sam.web.keqq.dao.Mapper;
import com.sam.web.keqq.model.PrimaryKey;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class BaseHandler<K extends Mapper<V>, V extends PrimaryKey> {

    protected void saveToDb(K mapper, V data) {
        if (mapper.exists(data)) {
            System.out.println("[更新] 数据存在，开始更新...");
            //获取id
            data.setId(mapper.selectByName(data).getId());
            //更新数据
            mapper.updateByPrimaryKeySelective(data);
            System.out.println("[更新] 更新成功..." + data.toString());
        } else {
            System.out.println("[插入] 数据不存在，开始插入...");
            //插入数据库
            mapper.insertSelective(data);
            System.out.println("[插入] 插入成功..." + data.toString());
        }
    }

}
