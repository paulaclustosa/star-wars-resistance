package com.letscode.starwarsresistance.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Item {
  WEAPON(4),
  AMMUNITION(3),
  WATER(2),
  FOOD(1);

  private int points;
}
