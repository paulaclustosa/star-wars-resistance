package com.letscode.starwarsresistance.domains.entities;

import com.letscode.starwarsresistance.domains.enums.Item;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Inventory {
  private Map<Item, Integer> items = new LinkedHashMap<>();
  private int points;

  public Inventory(int weaponAmount, int ammunitionAmount, int waterAmount, int foodAmount) {
    items.put(Item.WEAPON, weaponAmount);
    items.put(Item.AMMUNITION, ammunitionAmount);
    items.put(Item.WATER, waterAmount);
    items.put(Item.FOOD, foodAmount);
  }
}
