package com.ecse428.project.fitboi.dto;

import java.sql.Date;
import java.util.Objects;

import com.ecse428.project.fitboi.model.Sex;

public class UserDto {

    private String email;
    private String name;
    private String userName;
	private String password;
    private Date dOB;
    private int height;
    private Sex sex;

    public UserDto() {

    }

    public UserDto(String email, String name, String userName, String password, Date dOB, Sex sex, int height) {
        this.email = email;
        this.setName(name);
        this.setUserName(userName);
        this.setPassword(password);
        this.dOB = dOB;
        this.sex = sex;
        this.height = height;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDOB() {
        return this.dOB;
    }

    public void setDOB(Date dOB) {
        this.dOB = dOB;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex) {
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

    public UserDto dOB(Date dOB) {
        this.dOB = dOB;
        return this;
    }

    public UserDto sex(Sex sex) {
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
        return Objects.equals(email, userDto.email) && dOB == userDto.dOB && sex == userDto.sex && height == userDto.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, dOB, sex, height);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", age='" + getDOB() + "'" +
            ", sex='" + getSex() + "'" +
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