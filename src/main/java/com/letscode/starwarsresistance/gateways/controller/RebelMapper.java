package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RebelMapper {

  public Rebel toRebel(RebelRequest rebelRequest) {
    return Rebel.builder()
        .name(rebelRequest.getName())
        .age(rebelRequest.getAge())
        .gender(Gender.fromGenderRef(rebelRequest.getGender()))
        .location(new Location(rebelRequest.getLatitude(), rebelRequest.getLongitude(), rebelRequest.getGalaxyName()))
        .inventory(new Inventory(rebelRequest.getWeaponAmount(), rebelRequest.getAmmunitionAmount(), rebelRequest.getWaterAmount(), rebelRequest.getFoodAmount()))
        .build();
  }

}
