package com.javasanket.entity;

import java.time.LocalDate;
import java.util.List;

public class BookingDetails {

	LocalDate booking_date;
	List<Bookings> booking;
	public LocalDate getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}
	public List<Bookings> getBooking() {
		return booking;
	}
	public void setBooking(List<Bookings> booking) {
		this.booking = booking;
	}
	@Override
	public String toString() {
		return "BookingDetails [booking_date=" + booking_date + ", booking=" + booking + "]";
	}
	
	
}
