package com.almuhannd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almuhannd.model.UserBookingModel;

@Repository
public interface UserBookingRepository extends JpaRepository<UserBookingModel, Integer>{

	List<UserBookingModel> findAllByOrderByIdAsc();

	
	Page<UserBookingModel> findByUsernameLikeOrderByIdDesc(Pageable pageable, String name);
	
//	Page<UserBookingModel> findAll(Pageable pageable);

}
