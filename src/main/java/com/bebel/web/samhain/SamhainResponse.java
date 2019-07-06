package com.bebel.web.samhain;

import com.bebel.soclews.response.GeneralResponse;

/***
 * Recuperation de la sauvegarde Samhain
 */
public class SamhainResponse extends GeneralResponse {
    private String data;

    public String getData() {
        return data;
    }
    public void setData(final String data) {
        this.data = data;
    }
}
