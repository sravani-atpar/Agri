package com.example.agri.DTO;

public class ExamTotalDTO {
  private  long id;
  private  String examName;
  private double totalmarks;
  public int percentage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public double getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(double totalmarks) {
        this.totalmarks = totalmarks;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

}
