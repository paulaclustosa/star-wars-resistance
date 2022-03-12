package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Gender;
import lombok.Builder;
import lombok.Data;

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
}
