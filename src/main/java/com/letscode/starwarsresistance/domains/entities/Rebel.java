package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Gender;
import com.letscode.starwarsresistance.domains.enums.Item;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Rebel {
  private Long id;
  private final String name;
  private final int age;
  private final Gender gender;
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
    for (Item item : rejectedItems.getItems().keySet()) {
      if (this.getInventory().getItems().get(item) < rejectedItems.getItems().get(item)){
        errors.add(this.name + " doesn't have enough " + item + " for this transaction");
      }
    }
    if (!errors.isEmpty()) throw new BusinessValidationException(errors);
    return true;
  }
}
