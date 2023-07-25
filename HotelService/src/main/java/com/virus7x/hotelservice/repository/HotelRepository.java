package com.virus7x.hotelservice.repository;

import com.virus7x.hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {


}
