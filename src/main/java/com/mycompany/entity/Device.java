package com.mycompany.entity;

import javax.persistence.*;

@Entity
@Table (name = "devices")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = true, length = 2000)
	private String description;
	
	public Device(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Device() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
