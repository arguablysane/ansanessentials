package com.arguablysane.aseplayground.data.models;

/**
 * Created by administrator on 14/9/17.
 */

public class PersonModel {

	private long timestamp;
	private String name, location;
	private int age;

	public PersonModel() {}

	public PersonModel(long timestamp, String name, String location, int age) {
		this.timestamp = timestamp;
		this.name = name;
		this.location = location;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
