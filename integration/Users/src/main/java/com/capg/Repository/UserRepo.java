package com.capg.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.Model.UsersRegistration;



@Repository
public interface UserRepo extends JpaRepository<UsersRegistration,Integer> {
	
	
//	@Query(value="select e from UserBooking e where e.OrderId = :orderId")
	List<UsersRegistration> findByOrderId(int orderId);

}

