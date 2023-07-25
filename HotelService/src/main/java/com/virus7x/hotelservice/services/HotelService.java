package com.virus7x.hotelservice.services;

import com.virus7x.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String id);


}
