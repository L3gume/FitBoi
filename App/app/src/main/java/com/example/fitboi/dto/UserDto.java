package com.example.fitboi.dto;

import java.util.Objects;

public class UserDto {

    public String email;
    public int age;
    public boolean sex;
    public int weight;
    public int height;

    public UserDto(String email, int age, boolean sex, int weight, int height) {
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return this.sex;
    }

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    public UserDto age(int age) {
        this.age = age;
        return this;
    }

    public UserDto sex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public UserDto weight(int weight) {
        this.weight = weight;
        return this;
    }

    public UserDto height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) && age == userDto.age && sex == userDto.sex && weight == userDto.weight && height == userDto.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, age, sex, weight, height);
    }

    @Override
    public String toString() {
        return "{" +
                " email='" + getEmail() + "'" +
                ", age='" + getAge() + "'" +
                ", sex='" + isSex() + "'" +
                ", weight='" + getWeight() + "'" +
                ", height='" + getHeight() + "'" +
                "}";
    }

}
