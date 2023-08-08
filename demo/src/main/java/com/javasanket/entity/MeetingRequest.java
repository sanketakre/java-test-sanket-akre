package com.javasanket.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class MeetingRequest {
	String empId;
	LocalDate requestDate;
	LocalTime requestTime;
	MeetingSchedule meetingSchedule;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public LocalTime getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(LocalTime requestTime) {
		this.requestTime = requestTime;
	}
	
	public MeetingSchedule getMeetingSchedule() {
		return meetingSchedule;
	}
	public void setMeetingSchedule(MeetingSchedule meetingSchedule) {
		this.meetingSchedule = meetingSchedule;
	}
	@Override
	public int hashCode() {
		return Objects.hash(empId, meetingSchedule, requestDate, requestTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingRequest other = (MeetingRequest) obj;
		return Objects.equals(empId, other.empId) && Objects.equals(meetingSchedule, other.meetingSchedule)
				&& Objects.equals(requestDate, other.requestDate) && Objects.equals(requestTime, other.requestTime);
	}
	@Override
	public String toString() {
		return "MeetingRequest [empId=" + empId + ", requestDate=" + requestDate + ", requestTime=" + requestTime
				+ ", meetingSchedule=" + meetingSchedule + "]";
	}
	
	
		
	

}
