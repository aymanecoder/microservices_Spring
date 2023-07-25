package com.virus7x.hotelservice.services.impl;

import com.virus7x.hotelservice.entities.Hotel;
import com.virus7x.hotelservice.exceptions.ResourceNotFoundException;
import com.virus7x.hotelservice.repository.HotelRepository;
import com.virus7x.hotelservice.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository repository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return repository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found"));
    }
}
