package com.example.dream.thedog;

/**
 * Created by dream on 7/12/17.
 */

public class QuestionModel {
    private int id, yes, no;
    private String question, type;

    public QuestionModel(int id, int yes, int no, String question, String type) {
        this.id = id;
        this.question = question;
        this.yes = yes;
        this.no = no;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
