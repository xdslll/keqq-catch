package com.sam.web.keqq.model;

/**
 * @author xiads
 * @date 19/01/2018
 * @since
 */
public class LessonFrom {

    private int apply_num;
    private int price;
    private int recent_sign_num;
    private int course_good_percentage;
    private int attitude_rate;

    public int getCourse_good_percentage() {
        return course_good_percentage;
    }

    public void setCourse_good_percentage(int course_good_percentage) {
        this.course_good_percentage = course_good_percentage;
    }

    public int getAttitude_rate() {
        return attitude_rate;
    }

    public void setAttitude_rate(int attitude_rate) {
        this.attitude_rate = attitude_rate;
    }

    public int getApply_num() {
        return apply_num;
    }

    public void setApply_num(int apply_num) {
        this.apply_num = apply_num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRecent_sign_num() {
        return recent_sign_num;
    }

    public void setRecent_sign_num(int recent_sign_num) {
        this.recent_sign_num = recent_sign_num;
    }

    @Override
    public String toString() {
        return "LessonFrom{" +
                "apply_num=" + apply_num +
                ", price=" + price +
                ", recent_sign_num=" + recent_sign_num +
                ", course_good_percentage=" + course_good_percentage +
                ", attitude_rate=" + attitude_rate +
                '}';
    }
}
