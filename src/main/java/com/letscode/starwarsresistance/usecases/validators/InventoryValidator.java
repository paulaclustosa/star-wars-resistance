package com.letscode.starwarsresistance.usecases.validators;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryValidator {

  private static final String INVENTORY_MANDATORY_MSG = "Rebel's inventory is mandatory. ";

  public static List<String> validate(Inventory inventory) {
    List<String> errors = new ArrayList<>();

    if (!StringUtils.hasText(Integer.toString(inventory.getWeaponAmount()))) errors.add(INVENTORY_MANDATORY_MSG +
        "You have to declare it's weapon amount - even if equals 0 (zero).");
    if (!StringUtils.hasText(Integer.toString(inventory.getAmmunitionAmount()))) errors.add(INVENTORY_MANDATORY_MSG +
        "You have to declare it's ammunition amount - even if equals 0 (zero).");
    if (!StringUtils.hasText(Integer.toString(inventory.getWaterAmount()))) errors.add(INVENTORY_MANDATORY_MSG +
        "You have to declare it's water amount - even if equals 0 (zero).");
    if (!StringUtils.hasText(Integer.toString(inventory.getFoodAmount()))) errors.add(INVENTORY_MANDATORY_MSG +
        "You have to declare it's food amount - even if equals 0 (zero).");

    return errors;
  }

}
