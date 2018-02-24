package com.nicole.web.model;

public class Orgnization {

    private String residence;

    private String signDate;

    private String regDate;

    private String signBeginDate;

    private String signEndDate;

    private String regMon;

    public String getRegMon() {
        return regMon;
    }

    public void setRegMon(String regMon) {
        this.regMon = regMon;
    }

    public String getSignBeginDate() {
        return signBeginDate;
    }

    public void setSignBeginDate(String signBeginDate) {
        this.signBeginDate = signBeginDate;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(String signEndDate) {
        this.signEndDate = signEndDate;
    }

    @Override
    public String toString() {
        return "Orgnization{" +
                "residence='" + residence + '\'' +
                ", signDate='" + signDate + '\'' +
                ", regDate='" + regDate + '\'' +
                ", signBeginDate='" + signBeginDate + '\'' +
                ", signEndDate='" + signEndDate + '\'' +
                ", regMon='" + regMon + '\'' +
                '}';
    }
}
