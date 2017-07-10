package com.example.dream.thedog;

/**
 * Created by dream on 7/10/17.
 */

public class AccidentModel {
    private String accident;
    private String symptom;
    private String medication;
    private String note;

    public AccidentModel(String accident, String symptom, String medication, String note) {
        this.accident = accident;
        this.symptom = symptom;
        this.medication = medication;
        this.note = note;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
