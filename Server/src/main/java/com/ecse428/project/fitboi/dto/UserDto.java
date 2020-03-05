package com.ecse428.project.fitboi.dto;

import java.util.Objects;


public class UserDto {

    private String email;
    private String name;
    private String userName;
    private String password;
    private String dob;
    private int height;
    private String biologicalSex;

    public UserDto() {
    }

    public UserDto(String email, String name, String userName, String password, String dob, String biologicalSex, int height) {
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.dob = dob;
        this.height = height;
        this.biologicalSex = biologicalSex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(String biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        UserDto userDto = (UserDto) object;
        return height == userDto.height &&
                email.equals(userDto.email) &&
                name.equals(userDto.name) &&
                userName.equals(userDto.userName) &&
                password.equals(userDto.password) &&
                dob.equals(userDto.dob) &&
                biologicalSex.equals(userDto.biologicalSex);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), email, name, userName, password, dob, height, biologicalSex);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", height=" + height +
                ", biologicalSex='" + biologicalSex + '\'' +
                '}';
    }
}