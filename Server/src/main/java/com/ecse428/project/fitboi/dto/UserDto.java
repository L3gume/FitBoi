package com.ecse428.project.fitboi.dto;

import java.util.Objects;


public class UserDto {

    private String email;
    private String name;
    private String userName;
	private String password;
    private String dOB;
    private int height;
    private String sex;

    public UserDto() {

    }

    public UserDto(String email, String name, String userName, String password, String dOB, String sex, int height) {
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

    public String getDOB() {
        return this.dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
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

    public UserDto dOB(String dOB) {
        this.dOB = dOB;
        return this;
    }

    public UserDto sex(String sex) {
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
            " name='" + getName() + "'" +
            " userName='" + getUserName() + "'" +
            ", dOB='" + getDOB() + "'" +
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