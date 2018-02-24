package com.sam.web.keqq.cat;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public interface NetworkCatch<T> {

    T getData() throws UnsupportedEncodingException;
}
