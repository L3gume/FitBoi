package com.example.fitboi.dto;
import java.util.Objects;


public class UserDto {

    private String email;
    private String name;
    private String userName;
    private String password;
    private String dob;
    private Integer height;
    private String biologicalSex;

    public UserDto() {
    }

    public UserDto(String email, String name, String userName, String password, String dob, String biologicalSex, Integer height) {
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(String biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(userName, userDto.userName) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(dob, userDto.dob) &&
                Objects.equals(height, userDto.height) &&
                Objects.equals(biologicalSex, userDto.biologicalSex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, userName, password, dob, height, biologicalSex);
    }

    @Override
    public String toString() {
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