package com.virus7x.hotelservice.controllers;

import com.virus7x.hotelservice.entities.Hotel;
import com.virus7x.hotelservice.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody  Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));

    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }

}
