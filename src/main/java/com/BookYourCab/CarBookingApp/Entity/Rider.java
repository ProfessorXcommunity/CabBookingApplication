package com.BookYourCab.CarBookingApp.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean available;



}
