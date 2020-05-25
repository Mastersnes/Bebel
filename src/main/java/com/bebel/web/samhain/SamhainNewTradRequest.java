package com.bebel.web.samhain;

import com.bebel.soclews.request.KongregateRequest;

/***
 * Ajout d'une nouvelle traduction à Samhain
 */
public class SamhainNewTradRequest extends KongregateRequest {
    private String newTrad;

    public String getNewTrad() {
        return newTrad;
    }

    public void setNewTrad(String newTrad) {
        this.newTrad = newTrad;
    }
}
