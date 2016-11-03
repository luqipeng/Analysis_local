/**
 * Copyright 2016 aTool.org
 */
package com.buss.caiji.entity;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2016-09-26 17:52:51
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class StoreBean {

    @JsonProperty("feedbackInfo")
    private FeedbackInfo feedbackInfo;
    @JsonProperty("itemsCount")
    private int itemsCount;
    @JsonProperty("wishlistCount")
    private int wishlistCount;

    public FeedbackInfo getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(FeedbackInfo feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getWishlistCount() {
        return wishlistCount;
    }

    public void setWishlistCount(int wishlistCount) {
        this.wishlistCount = wishlistCount;
    }
}