package com.ecse428.project.fitboi.dto;

public class MetricDto {

    private int id;
    private String date;
    private int exerciseSpending;
    private String footNote;

    public MetricDto() {
    }

    public MetricDto(int id, String date, int exerciseSpending, String footNote) {
        this.id = id;
        this.date = date;
        this.exerciseSpending = exerciseSpending;
        this.footNote = footNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return String return the footNote
     */
    public String getFootNote() {
        return footNote;
    }

    /**
     * @param footNote the footNote to set
     */
    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }


    public int getExerciseSpending() {
        return exerciseSpending;
    }

    public void setExerciseSpending(int exerciseSpending) {
        this.exerciseSpending = exerciseSpending;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MetricDto metricDto = (MetricDto) object;
        return id == metricDto.id &&
                exerciseSpending == metricDto.exerciseSpending &&
                date.equals(metricDto.date);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, date, exerciseSpending);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "MetricDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", exerciseSpending=" + exerciseSpending +
                ", footNote=" + footNote +
                '}';
    }

  
}
