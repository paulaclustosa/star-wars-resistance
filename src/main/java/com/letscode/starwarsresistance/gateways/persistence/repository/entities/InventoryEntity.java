package com.letscode.starwarsresistance.gateways.persistence.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class InventoryEntity {

  @Column (name = "WEAPON_AMOUNT")
  private int weaponAmount;
  @Column (name = "AMMUNITION_AMOUNT")
  private int ammunitionAmount;
  @Column (name = "WATER_AMOUNT")
  private int waterAmount;
  @Column (name = "FOOD_AMOUNT")
  private int foodAmount;

}
