package com.lab.vxt.heywake.models;

/**
 * Created by Thuy Nguyen on 12/22/2017.
 */

public class MultipleChoiceQuestion {
    private String question;
    private String false1;
    private String false2;
    private String false3;
    private String true4;
    private int isUsed;
    private int id;

    public MultipleChoiceQuestion(String question, String false1, String false2, String false3, String true4) {
        this.question = question;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
        this.true4 = true4;
    }

    public MultipleChoiceQuestion(int id, String question, String false1, String false2, String false3, String true4, int isUsed) {
        this.id = id;
        this.question = question;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
        this.true4 = true4;
        this.isUsed = isUsed;
    }

    public MultipleChoiceQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFalse1() {
        return false1;
    }

    public void setFalse1(String false1) {
        this.false1 = false1;
    }

    public String getFalse2() {
        return false2;
    }

    public void setFalse2(String false2) {
        this.false2 = false2;
    }

    public String getFalse3() {
        return false3;
    }

    public void setFalse3(String false3) {
        this.false3 = false3;
    }

    public String getTrue4() {
        return true4;
    }

    public void setTrue4(String true4) {
        this.true4 = true4;
    }

    public int isUsed() {
        return isUsed;
    }

    public void setUsed(int used) {
        isUsed = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "question='" + question + '\'' +
                ", false1='" + false1 + '\'' +
                ", false2='" + false2 + '\'' +
                ", false3='" + false3 + '\'' +
                ", true4='" + true4 + '\'' +
                ", isUsed=" + isUsed +
                ", id=" + id +
                '}';
    }
}
