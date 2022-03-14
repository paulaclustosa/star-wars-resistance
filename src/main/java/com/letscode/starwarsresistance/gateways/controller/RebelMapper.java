package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RebelMapper {

  public Rebel toRebel (RebelRequest rebelRequest) {
    return Rebel.builder()
        .name(rebelRequest.getName())
        .age(rebelRequest.getAge())
        .gender(rebelGenderEntryValidation(rebelRequest))
        .location(new Location(rebelRequest.getLatitude(), rebelRequest.getLongitude(), rebelRequest.getGalaxyName()))
        .inventory(new Inventory(rebelRequest.getWeaponAmount(), rebelRequest.getAmmunitionAmount(),
            rebelRequest.getWaterAmount(), rebelRequest.getFoodAmount()))
        .build();
  }

  public static Gender rebelGenderEntryValidation(RebelRequest rebelRequest) {
    if (Objects.equals(rebelRequest.getGender(), "F")) return Gender.FEMALE;
    if (Objects.equals(rebelRequest.getGender(), "M")) return Gender.MALE;
    else return null;
  }

}
