package com.letscode.starwarsresistance.usecases.validators;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RebelValidator {

  LocationValidator locationValidator;
  InventoryValidator inventoryValidator;

  public List<String> validate(Rebel rebel) {
    List<String> errors = new ArrayList<>();

    if (rebel == null) return List.of("Rebel cannot be null!");
    if (rebel.getId() != null) errors.add("Rebel already registered.");
    if (!StringUtils.hasText(rebel.getName())) errors.add("Rebel's name is mandatory.");
    if (!StringUtils.hasText(Integer.toString(rebel.getAge()))) errors.add("Rebel's age is mandatory.");
    if (rebel.getGender() != Gender.FEMALE || rebel.getGender() != Gender.MALE) errors.add("Invalid gender entry.");

    List<String> locationErrors = locationValidator.validate(rebel.getLocation());
    if (!locationErrors.isEmpty()) errors.addAll(locationErrors);

    List<String> inventoryErrors = inventoryValidator.validate(rebel.getInventory());
    if (!inventoryErrors.isEmpty()) errors.addAll(inventoryErrors);

    return errors;
  }

}
