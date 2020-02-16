package com.example.fitboi.dto;

import java.util.Objects;

public class UserDto {

    private String email;
    private String name;
    private String userName;
    private String password;
    private int age;
    private boolean sex;
    private int height;

    public UserDto() {

    }

    public UserDto(String email, String name, String userName, String password, int age, int height, boolean sex) {
        this.email = email;
        this.setName(name);
        this.setUserName(userName);
        this.setPassword(password);
        this.age = age;
        this.sex = sex;
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
        return Objects.equals(email, userDto.email) && age == userDto.age && sex == userDto.sex && height == userDto.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, age, sex, height);
    }

    @Override
    public String toString() {
        return "{" +
                " email='" + getEmail() + "'" +
                ", age='" + getAge() + "'" +
                ", sex='" + isSex() + "'" +
                ", height='" + getHeight() + "'" +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
