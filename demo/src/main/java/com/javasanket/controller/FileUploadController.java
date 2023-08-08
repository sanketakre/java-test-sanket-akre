package com.javasanket.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javasanket.entity.BookingDetails;
import com.javasanket.entity.Bookings;
import com.javasanket.entity.MeetingRequest;
import com.javasanket.entity.MeetingSchedule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fileUplaodApp")
public class FileUploadController {
	Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@GetMapping("/health")
	public String checkHealth() {
		return "up and running";
	}


	@PostMapping(value = "/fileUpload")
	ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		Logger log = LoggerFactory.getLogger(FileUploadController.class);
		List<MeetingRequest> meetingDetailsList = null;
		try {
			String currentLine = "";
			BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()));
			int lineNumber = 1;
			MeetingRequest meetingRequest = null;
			MeetingSchedule meetingSchedule = null;
			meetingDetailsList = new ArrayList<>();
			
			// Read uploaded file and put elements in MeetingRequest and MeetingSchedule 
			while ((currentLine = buffer.readLine()) != null) {
				if (lineNumber > 1) {
					
					if (lineNumber % 2 == 0) { //Capture even line number from uploaded file
						meetingRequest = new MeetingRequest();
						String[] result = currentLine.split("\\s");
						meetingRequest.setRequestDate(LocalDate.parse(result[0]));
						meetingRequest.setRequestTime(LocalTime.parse(result[1]));
						meetingRequest.setEmpId(result[2]);
					}

					else { ////Capture odd line number from uploaded file
						String[] result = currentLine.split("\\s");
						meetingSchedule = new MeetingSchedule();
						meetingSchedule.setMeetingDate(LocalDate.parse(result[0]));
						meetingSchedule.setMeetingTime(LocalTime.parse(result[1]));
						meetingSchedule.setMeetingDuration(Long.parseLong(result[2]));
						meetingSchedule.setEmpId(meetingRequest.getEmpId());
						meetingRequest.setMeetingSchedule(meetingSchedule);
						meetingDetailsList.add(meetingRequest);
						meetingRequest = null;
						meetingSchedule = null;
						
					}
					
				}
				lineNumber++;
			}
			buffer.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		

		// Sort the Meeting Detail list by Request date and Request time. 
		// Then sort with Meeting Schedule Data and Time
		List<MeetingSchedule> output2 = meetingDetailsList.stream()
				.sorted(Comparator.comparing( MeetingRequest::getRequestDate).thenComparing(MeetingRequest::getRequestTime))	
				.map(obj -> obj.getMeetingSchedule())
				.distinct()
				.sorted(Comparator.comparing(MeetingSchedule::getMeetingDate).thenComparing(MeetingSchedule::getMeetingTime))
				.collect(Collectors.toList());	
		
		// Create Booking List Details.
		BookingDetails bookingDetails = null;
		Bookings bookings = null;
		List<BookingDetails> listBookingDetails = new ArrayList<>();
		List<Bookings> listBookings = new ArrayList<>();
		for(int i=0;i<output2.size(); i++) {
			bookingDetails = new BookingDetails();
			bookings = new Bookings();		
			if(listBookings ==null)
				listBookings = new ArrayList<>();
			bookings.setEmp_id(output2.get(i).getEmpId());
			bookings.setStart_time(output2.get(i).getMeetingTime());
			bookings.setEnd_time(output2.get(i).getMeetingTime().plusHours(output2.get(i).getMeetingDuration()));
			listBookings.add(bookings);
			bookingDetails.setBooking_date(output2.get(i).getMeetingDate());
			bookingDetails.setBooking(listBookings);		
			listBookingDetails.add(bookingDetails);
			bookingDetails = null;
			bookings = null;
			listBookings=null;
		}
		
		return ResponseEntity.ok().body(listBookingDetails);
	}
	
	
	// Identify Orvelap
	private boolean isOverLap(MeetingSchedule meetingSchedule, MeetingSchedule meetingSchedule2) {
		if(meetingSchedule.getMeetingTime().plusHours(meetingSchedule.getMeetingDuration())
				.compareTo(meetingSchedule2.getMeetingTime()) > 0 ) {
			return true; // Overlapped
		}		
		return false; // No Overlap
	}

	

}
