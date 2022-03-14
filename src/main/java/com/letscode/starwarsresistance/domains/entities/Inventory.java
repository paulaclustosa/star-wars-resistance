package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Item;
import lombok.Data;

@Data
public class Inventory {

  private int weaponAmount;
  private int ammunitionAmount;
  private int waterAmount;
  private int foodAmount;
  private int points;

  public Inventory(int weaponAmount, int ammunitionAmount, int waterAmount, int foodAmount) {
    this.weaponAmount = weaponAmount;
    this.ammunitionAmount = ammunitionAmount;
    this.waterAmount = waterAmount;
    this.foodAmount = foodAmount;
  }

  public void setPoints() {
    this.points = weaponAmount * Item.WEAPON.getPoints() +
        ammunitionAmount * Item.AMMUNITION.getPoints() +
        waterAmount * Item.WATER.getPoints() +
        foodAmount * Item.FOOD.getPoints();
  }
}
