package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Gender;
import com.letscode.starwarsresistance.domains.enums.Item;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//  public boolean hasItems(Inventory rejectedItems) {
//    for (Item item : items.keySet()) {
//
//    }
//    else true;
//  }
}
