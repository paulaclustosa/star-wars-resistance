package com.letscode.starwarsresistance.usecases.validators;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import com.letscode.starwarsresistance.domains.enums.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateRebelValidator {

  public List<String> validate(Rebel rebel) {
    List<String> errors = new ArrayList<>();

    if (rebel == null) return List.of("Rebel cannot be null!");
    if (rebel.getId() != null) errors.add("Rebel already registered.");
    if (!StringUtils.hasText(rebel.getName())) errors.add("Rebel's name is mandatory.");
    if (!StringUtils.hasText(Integer.toString(rebel.getAge()))) errors.add("Rebel's age is mandatory.");
    if (rebel.getGender() != Gender.FEMALE || rebel.getGender() != Gender.MALE) errors.add("Invalid gender entry.");
    if (!StringUtils.hasText(String.valueOf(rebel.getLocation().getLatitude()))) errors.add("Rebel's location is mandatory (latitude).");
    if (!StringUtils.hasText(String.valueOf(rebel.getLocation().getLongitude()))) errors.add("Rebel's location is mandatory (longitude).");
    if (!StringUtils.hasText(rebel.getLocation().getGalaxyName())) errors.add("Rebel's location is mandatory (galaxy's name).");
    if (!StringUtils.hasText(Integer.toString(rebel.getInventory().getItems().get(Item.WEAPON)))) errors.add("Rebel's inventory is mandatory. " +
        "You have to declare it's weapon amount - even if equals 0, zero.");
    if (!StringUtils.hasText(Integer.toString(rebel.getInventory().getItems().get(Item.AMMUNITION)))) errors.add("Rebel's inventory is mandatory. " +
        "You have to declare it's ammunition amount - even if equals 0, zero.");
    if (!StringUtils.hasText(Integer.toString(rebel.getInventory().getItems().get(Item.WATER)))) errors.add("Rebel's inventory is mandatory. " +
        "You have to declare it's water amount - even if equals 0, zero.");
    if (!StringUtils.hasText(Integer.toString(rebel.getInventory().getItems().get(Item.FOOD)))) errors.add("Rebel's inventory is mandatory. " +
        "You have to declare it's food amount - even if equals 0, zero.");

    return errors;
  }

}
