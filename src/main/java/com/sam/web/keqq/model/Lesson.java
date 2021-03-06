package com.sam.web.keqq.model;

public class Lesson implements PrimaryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.id
     *
     * @mbggenerated
     */
    private Integer id;

    private Integer cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.price
     *
     * @mbggenerated
     */
    private Long price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.source
     *
     * @mbggenerated
     */
    private String source;

    private int type;

    private String name;

    private int aid;

    private int industry2nd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.comment_total_num
     *
     * @mbggenerated
     */
    private Integer commentTotalNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.comment_rate_num
     *
     * @mbggenerated
     */
    private Integer commentRateNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.comment_good_num
     *
     * @mbggenerated
     */
    private Integer commentGoodNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.comment_bad_num
     *
     * @mbggenerated
     */
    private Integer commentBadNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.comment_medium_num
     *
     * @mbggenerated
     */
    private Integer commentMediumNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.total_apply_num
     *
     * @mbggenerated
     */
    private Integer totalApplyNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lesson.recent_apply_num
     *
     * @mbggenerated
     */
    private Integer recentApplyNum;

    private int apply_num;



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.id
     *
     * @return the value of lesson.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.id
     *
     * @param id the value for lesson.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.title
     *
     * @return the value of lesson.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.title
     *
     * @param title the value for lesson.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.url
     *
     * @return the value of lesson.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.url
     *
     * @param url the value for lesson.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.price
     *
     * @return the value of lesson.price
     *
     * @mbggenerated
     */
    public Long getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.price
     *
     * @param price the value for lesson.price
     *
     * @mbggenerated
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.source
     *
     * @return the value of lesson.source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.source
     *
     * @param source the value for lesson.source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.comment_total_num
     *
     * @return the value of lesson.comment_total_num
     *
     * @mbggenerated
     */
    public Integer getCommentTotalNum() {
        return commentTotalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.comment_total_num
     *
     * @param commentTotalNum the value for lesson.comment_total_num
     *
     * @mbggenerated
     */
    public void setCommentTotalNum(Integer commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.comment_rate_num
     *
     * @return the value of lesson.comment_rate_num
     *
     * @mbggenerated
     */
    public Integer getCommentRateNum() {
        return commentRateNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.comment_rate_num
     *
     * @param commentRateNum the value for lesson.comment_rate_num
     *
     * @mbggenerated
     */
    public void setCommentRateNum(Integer commentRateNum) {
        this.commentRateNum = commentRateNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.comment_good_num
     *
     * @return the value of lesson.comment_good_num
     *
     * @mbggenerated
     */
    public Integer getCommentGoodNum() {
        return commentGoodNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.comment_good_num
     *
     * @param commentGoodNum the value for lesson.comment_good_num
     *
     * @mbggenerated
     */
    public void setCommentGoodNum(Integer commentGoodNum) {
        this.commentGoodNum = commentGoodNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.comment_bad_num
     *
     * @return the value of lesson.comment_bad_num
     *
     * @mbggenerated
     */
    public Integer getCommentBadNum() {
        return commentBadNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.comment_bad_num
     *
     * @param commentBadNum the value for lesson.comment_bad_num
     *
     * @mbggenerated
     */
    public void setCommentBadNum(Integer commentBadNum) {
        this.commentBadNum = commentBadNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.comment_medium_num
     *
     * @return the value of lesson.comment_medium_num
     *
     * @mbggenerated
     */
    public Integer getCommentMediumNum() {
        return commentMediumNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.comment_medium_num
     *
     * @param commentMediumNum the value for lesson.comment_medium_num
     *
     * @mbggenerated
     */
    public void setCommentMediumNum(Integer commentMediumNum) {
        this.commentMediumNum = commentMediumNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.total_apply_num
     *
     * @return the value of lesson.total_apply_num
     *
     * @mbggenerated
     */
    public Integer getTotalApplyNum() {
        return totalApplyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.total_apply_num
     *
     * @param totalApplyNum the value for lesson.total_apply_num
     *
     * @mbggenerated
     */
    public void setTotalApplyNum(Integer totalApplyNum) {
        this.totalApplyNum = totalApplyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lesson.recent_apply_num
     *
     * @return the value of lesson.recent_apply_num
     *
     * @mbggenerated
     */
    public Integer getRecentApplyNum() {
        return recentApplyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lesson.recent_apply_num
     *
     * @param recentApplyNum the value for lesson.recent_apply_num
     *
     * @mbggenerated
     */
    public void setRecentApplyNum(Integer recentApplyNum) {
        this.recentApplyNum = recentApplyNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getIndustry2nd() {
        return industry2nd;
    }

    public void setIndustry2nd(int industry2nd) {
        this.industry2nd = industry2nd;
    }

    public int getApply_num() {
        return apply_num;
    }

    public void setApply_num(int apply_num) {
        this.apply_num = apply_num;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", cid=" + cid +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", source='" + source + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", aid=" + aid +
                ", industry2nd=" + industry2nd +
                ", commentTotalNum=" + commentTotalNum +
                ", commentRateNum=" + commentRateNum +
                ", commentGoodNum=" + commentGoodNum +
                ", commentBadNum=" + commentBadNum +
                ", commentMediumNum=" + commentMediumNum +
                ", totalApplyNum=" + totalApplyNum +
                ", recentApplyNum=" + recentApplyNum +
                ", apply_num=" + apply_num +
                '}';
    }
}