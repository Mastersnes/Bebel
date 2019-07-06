package com.bebel.web.samhain;

import com.bebel.soclews.request.KongregateRequest;

/***
 * Sauvegarde relative à Samhain
 */
public class SamhainSaveRequest extends KongregateRequest {
    private String data;

    public String getData() {
        return data;
    }
    public void setData(final String data) {
        this.data = data;
    }
}
