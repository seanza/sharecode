package com.model;

public class Student{
	private int id;
	private String name;
	private String password;
	private String group;
	
	public Student(){
		super();
	}

	public Student(int id, String name, String password,String group) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.group = group;
	}
	
	public Student(String name, String password,String group) {
		super();
		this.name = name;
		this.password = password;
		this.group = group;
	}
	public Student(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	
}

	
