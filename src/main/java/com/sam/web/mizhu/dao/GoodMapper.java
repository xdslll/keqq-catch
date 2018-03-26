package com.sam.web.mizhu.dao;

import com.sam.web.keqq.dao.Mapper;
import com.sam.web.mizhu.model.Good;

import java.util.List;

/**
 * @author xiads
 * @date 07/03/2018
 * @since
 */
public interface GoodMapper extends Mapper<Good> {

    List<Good> selectAll();

}
