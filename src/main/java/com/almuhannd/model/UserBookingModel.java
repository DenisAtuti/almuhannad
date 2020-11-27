package com.almuhannd.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "bookings")
public class UserBookingModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	private LocalDateTime enteredAt;
	
	@Column(name = "user_name")
	@Size(min=6, message="Your should be more that 6 characters")
	private String username;
	
	@Email(message="Please enter a valid email")
	private String email;
	
	@Size(min=6, message="The phone number should be more that 6 numbers")
	private String contact;

	@Column(name = "cleaning_date")
	private String cleaningDate;
	
	@Size(min=3, message="Your location should be more that 3 characters")
	private String location;
	
	private String hours;
	
	private String maids;
	
	@Column(name = "cleaning_schedule")
	private String cleaningSchedule;
	
	@Size(min=6, message="Your description should be more that 6 characters")
	private String description;
}
