package com.magicrecipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 04/24/18.
 */

public class Recepies {
    @SerializedName("results")
    @Expose
    private List<SubDetails> receipe = null;

    public List<SubDetails> getReceipes() {
        return receipe;
    }

    public void setStudents(List<SubDetails> receipe) {
        this.receipe = receipe;
    }
}
