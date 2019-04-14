package com.bebel.web.yule;

import com.bebel.soclews.response.GeneralResponse;

/***
 * Recuperation de la sauvegarde Yule
 */
public class YuleResponse extends GeneralResponse {
    private String data;

    public String getData() {
        return data;
    }
    public void setData(final String data) {
        this.data = data;
    }
}
