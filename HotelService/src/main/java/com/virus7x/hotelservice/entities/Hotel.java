package com.virus7x.hotelservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    private String location;
    private String about;


}
