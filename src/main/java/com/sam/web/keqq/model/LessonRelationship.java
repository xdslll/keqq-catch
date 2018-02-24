package com.sam.web.keqq.model;

import java.util.List;

/**
 * @author xiads
 * @date 19/01/2018
 * @since
 */
public class LessonRelationship {

    private List<LessonDetail> relationships;

    public List<LessonDetail> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<LessonDetail> relationships) {
        this.relationships = relationships;
    }
}
