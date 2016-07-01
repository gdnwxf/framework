package com.wch.pojo;

import java.io.Serializable;

import com.wch.annotation.compent.Controller;

@Controller
public class User extends Person implements Serializable ,Cloneable {
	
	 /**    */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String password;
	private String email;
	private String qq;
	private String gender;
	private String birthday;
	private String status;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
	}
	
	private Animal animal ;
	
	
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", qq=" + qq + ", gender=" + gender + ", birthday=" + birthday + ", status=" + status + "]";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
