package com.almuhannd.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almuhannd.model.UserBookingModel;
import com.almuhannd.repository.UserBookingRepository;

import lombok.Data;

@Service
public class UserBookingService {
	
	@Autowired
	private UserBookingRepository bookingRepository;
	
	public void saveOrder(UserBookingModel order) {
		bookingRepository.save(order);
	}
	

//	public Page<UserBookingModel> findByName(String name) {
//		int bookingPages;
//		return Page<UserBookingModel> bookingPages = bookingRepository.findByUsernameLike(null, "%"+name+"%");
//		
//		
//	}
	
	public int getAnnualEarning() {
		List<UserBookingModel> bookingList = bookingRepository.findAll();
		ArrayList<Integer> hoursArray = new ArrayList<Integer>();
				
		bookingList.forEach(data -> {
			LocalDate currentDate = LocalDate.now(); // 2016-06-17
//			DayOfWeek dow = currentDate.getDayOfWeek(); // FRIDAY
//			int dom = currentDate.getDayOfMonth(); // 17
//			int doy = currentDate.getDayOfYear(); // 169
//			Month m = currentDate.getMonth(); // JUNE
//			int mon = currentDate.getMonthValue();
			int y = currentDate.getYear(); // 2016

			
			String bookingDate = data.getCleaningDate();
			LocalDate date = LocalDate.parse(bookingDate);
			
			int bookingYear = date.getYear();
			
			if(bookingYear == y) {
				hoursArray.add(Integer.parseInt(data.getHours()));
				
			}
			
		});
		
		int hours = 0;
		
		
		for (int i = 0; i < hoursArray.size(); i++) {
			hours = hours + hoursArray.get(i) ;
		}
		
		
		
		int totalHours = 25 * hours;
		
		return totalHours;
		
	}
	
	public int getMonthyEarning() {
		List<UserBookingModel> bookingList = bookingRepository.findAll();
		ArrayList<Integer> hoursArray = new ArrayList<Integer>();
				
		bookingList.forEach(data -> {
			LocalDate currentDate = LocalDate.now(); // 2016-06-17
//			DayOfWeek dow = currentDate.getDayOfWeek(); // FRIDAY
//			int dom = currentDate.getDayOfMonth(); // 17
//			int doy = currentDate.getDayOfYear(); // 169
//			Month m = currentDate.getMonth(); // JUNE
			int mon = currentDate.getMonthValue();
//			int y = currentDate.getYear(); // 2016

			
			String bookingDate = data.getCleaningDate();
			LocalDate date = LocalDate.parse(bookingDate);
			
			int bookingMonth = date.getMonthValue();
//			int bookingYear = date.getYear();
			
			if(bookingMonth == mon) {
				hoursArray.add(Integer.parseInt(data.getHours()));
			}
			
		});
		
		int hours = 0;
		
		
		for (int i = 0; i < hoursArray.size(); i++) {
			hours = hours + hoursArray.get(i) ;
		}
		
		
		int totalHours = 25 * hours;
		
		return totalHours;
		
	}
	
	public long getEntries() {
		return bookingRepository.count();
		
	}
	
	public int getTodayTasks() {
		List<UserBookingModel> bookingList = bookingRepository.findAll();
		ArrayList<Integer> todayTask = new ArrayList<Integer>();
		bookingList.forEach(data -> {
			LocalDate currentDate = LocalDate.now();
			int todayDate = currentDate.getDayOfMonth();
			
			String bookingDate = data.getCleaningDate();
			LocalDate date = LocalDate.parse(bookingDate);
			int bookingDay = date.getDayOfMonth();
			
			if(todayDate == bookingDay) {
				todayTask.add(data.getId());
			}
			
		});
		
		return todayTask.size();
		
	}
	
	
	
}
