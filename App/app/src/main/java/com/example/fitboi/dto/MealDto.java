package com.example.fitboi.dto;

public class MealDto {

    private int id;
    private String mealType;

    public MealDto() {
    }

    public MealDto(int id, String mealType) {
        this.id = id;
        this.mealType = mealType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MealDto mealDto = (MealDto) object;
        return id == mealDto.id &&
                mealType.equals(mealDto.mealType);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, mealType);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "MealDto{" +
                "id=" + id +
                ", mealType='" + mealType + '\'' +
                '}';
    }
}
