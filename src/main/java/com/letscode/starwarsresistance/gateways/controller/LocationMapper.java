package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location toLocation (LocationRequest locationRequest) {
        return Location.builder()
                .latitude(locationRequest.getLatitude())
                .longitude(locationRequest.getLongitude())
                .galaxyName(locationRequest.getGalaxyName())
                .build();
    }
}
