package com.sam.web.keqq.model;

import java.util.List;

/**
 * @author xiads
 * @date 23/01/2018
 * @since
 */
public class LessonItemList<T> {

    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
