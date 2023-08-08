package com.javasanket.entity;

import java.time.LocalTime;

public class Bookings {
	String emp_id;
	LocalTime start_time;
	LocalTime end_time;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public LocalTime getStart_time() {
		return start_time;
	}
	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}
	public LocalTime getEnd_time() {
		return end_time;
	}
	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "Bookings [emp_id=" + emp_id + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
	
}
