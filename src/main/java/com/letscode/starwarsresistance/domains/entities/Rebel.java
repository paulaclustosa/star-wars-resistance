package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Gender;
import com.letscode.starwarsresistance.domains.enums.Item;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Rebel {
  private Long id;
  private String name;
  private int age;
  private Gender gender;
  private Location location;
  private Inventory inventory;
  private boolean isTraitor = false;
  private int numberOfReportsAsTraitor = 0;

  @Builder
  public Rebel(String name, int age, Gender gender, Location location, Inventory inventory) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.location = location;
    this.inventory = inventory;
  }

  public boolean hasItems(Inventory rejectedItems) {
    List<String> errors = new ArrayList<>();

    if (this.getInventory().getAmmunitionAmount() < rejectedItems.getAmmunitionAmount()) errors.add(this.name + " doesn't have enough ammunition for this transaction");
    if (this.getInventory().getWeaponAmount() < rejectedItems.getWeaponAmount()) errors.add(this.name + " doesn't have enough weapons for this transaction");
    if (this.getInventory().getFoodAmount() < rejectedItems.getFoodAmount()) errors.add(this.name + " doesn't have enough food for this transaction");
    if (this.getInventory().getWaterAmount() < rejectedItems.getWaterAmount()) errors.add(this.name + " doesn't have enough water for this transaction");

    if (!errors.isEmpty()) throw new BusinessValidationException(errors);
    return true;
  }
}
