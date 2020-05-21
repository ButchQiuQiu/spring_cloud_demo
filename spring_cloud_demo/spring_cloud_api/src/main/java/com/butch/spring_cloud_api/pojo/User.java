package com.butch.spring_cloud_api.pojo;

import java.io.Serializable;

/**
 * User
 */
public class User implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;

	public User() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String username, String password, String name) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", username=" + username + "]";
	}
	
}