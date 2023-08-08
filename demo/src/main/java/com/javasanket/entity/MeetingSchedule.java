package com.javasanket.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class MeetingSchedule {
	String empId;
	LocalDate meetingDate;
	LocalTime meetingTime;
	Long meetingDuration;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public LocalDate getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}
	public LocalTime getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(LocalTime meetingTime) {
		this.meetingTime = meetingTime;
	}
	public Long getMeetingDuration() {
		return meetingDuration;
	}
	public void setMeetingDuration(Long meetingDuration) {
		this.meetingDuration = meetingDuration;
	}
	@Override
	public int hashCode() {
		return Objects.hash(empId, meetingDate, meetingDuration, meetingTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingSchedule other = (MeetingSchedule) obj;
		return Objects.equals(empId, other.empId) && Objects.equals(meetingDate, other.meetingDate)
				&& Objects.equals(meetingDuration, other.meetingDuration)
				&& Objects.equals(meetingTime, other.meetingTime);
	}
	@Override
	public String toString() {
		return "MeetingSchedule [empId=" + empId + ", meetingDate=" + meetingDate + ", meetingTime=" + meetingTime
				+ ", meetingDuration=" + meetingDuration + "]";
	}
	
	
	
	

}
