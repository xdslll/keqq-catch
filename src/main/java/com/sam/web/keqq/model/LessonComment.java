package com.sam.web.keqq.model;

/**
 * @author xiads
 * @date 19/01/2018
 * @since
 */
public class LessonComment {

    private int good_num;
    private int all_num;
    private int id;
    private int medium_num;
    private int bad_num;
    private int good_percentage;

    public int getGood_num() {
        return good_num;
    }

    public void setGood_num(int good_num) {
        this.good_num = good_num;
    }

    public int getAll_num() {
        return all_num;
    }

    public void setAll_num(int all_num) {
        this.all_num = all_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedium_num() {
        return medium_num;
    }

    public void setMedium_num(int medium_num) {
        this.medium_num = medium_num;
    }

    public int getBad_num() {
        return bad_num;
    }

    public void setBad_num(int bad_num) {
        this.bad_num = bad_num;
    }

    public int getGood_percentage() {
        return good_percentage;
    }

    public void setGood_percentage(int good_percentage) {
        this.good_percentage = good_percentage;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "good_num=" + good_num +
                ", all_num=" + all_num +
                ", id=" + id +
                ", medium_num=" + medium_num +
                ", bad_num=" + bad_num +
                ", good_percentage=" + good_percentage +
                '}';
    }
}
