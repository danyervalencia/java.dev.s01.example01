package com.java.dev01.exercise01.pojos;

import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private int persId;
    private String persPaternal;
    private String persMaternal;
    private String persName;

    public Persona() {
    }

    public Persona(int persId, String persPaternal, String persName) {
        this.persId = persId;
        this.persPaternal = persPaternal;
        this.persName = persName;
    }

    public int getPersId() {
        return this.persId;
    }

    public void setPersId(int persId) {
        this.persId = persId;
    }

    public String getPersPaternal() {
        return this.persPaternal;
    }

    public void setPersPaternal(String persPaternal) {
        this.persPaternal = persPaternal;
    }

    public String getPersMaternal() {
        return this.persMaternal;
    }

    public void setPersMaternal(String persMaternal) {
        this.persMaternal = persMaternal;
    }

    public String getPersName() {
        return this.persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }
}