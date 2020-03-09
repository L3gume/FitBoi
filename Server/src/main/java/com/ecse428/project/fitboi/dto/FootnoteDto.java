package com.ecse428.project.fitboi.dto;

public class FootnoteDto {

    private int id;
    private String date;
    private String note;

    public FootnoteDto() {

    }

    public FootnoteDto(int id, String date, String note) {
        this.id = id;
        this.date = date;
        this.note = note;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "FootnoteDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FootnoteDto that = (FootnoteDto) object;
        return getId() == that.getId() &&
                java.util.Objects.equals(getDate(), that.getDate()) &&
                getNote().equals(that.getNote());
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), getId(), getDate(), getNote());
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
