/**
 * Copyright 2016 aTool.org
 */
package com.buss.caiji.entity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2016-09-26 17:52:51
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class FeedbackInfo {

    @JsonProperty("sellerLevel")
    private String sellerLevel;
    @JsonProperty("sellerPositiveNum")
    private int sellerPositiveNum;
    @JsonProperty("sellerPositiveRate")
    private String sellerPositiveRate;
    @JsonProperty("sellerScore")
    private int sellerScore;
    @JsonProperty("sellerTotalNum")
    private int sellerTotalNum;


    public String getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(String sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public int getSellerPositiveNum() {
        return sellerPositiveNum;
    }

    public void setSellerPositiveNum(int sellerPositiveNum) {
        this.sellerPositiveNum = sellerPositiveNum;
    }

    public String getSellerPositiveRate() {
        return sellerPositiveRate;
    }

    public void setSellerPositiveRate(String sellerPositiveRate) {
        this.sellerPositiveRate = sellerPositiveRate;
    }

    public int getSellerScore() {
        return sellerScore;
    }

    public void setSellerScore(int sellerScore) {
        this.sellerScore = sellerScore;
    }

    public int getSellerTotalNum() {
        return sellerTotalNum;
    }

    public void setSellerTotalNum(int sellerTotalNum) {
        this.sellerTotalNum = sellerTotalNum;
    }
}