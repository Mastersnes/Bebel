package com.bebel.web.yule;

import com.bebel.soclews.request.KongregateRequest;

/***
 * Sauvegarde relative à Yule
 */
public class YuleSaveRequest extends KongregateRequest {
    private String data;

    public String getData() {
        return data;
    }
    public void setData(final String data) {
        this.data = data;
    }
}
