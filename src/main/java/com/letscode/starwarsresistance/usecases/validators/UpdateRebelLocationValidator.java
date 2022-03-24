package com.letscode.starwarsresistance.usecases.validators;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UpdateRebelLocationValidator {
    public List<String> validate(Rebel rebel, Location location) {
      List<String> errors = new ArrayList<>();

      List<String> locationErrors = LocationValidator.validate(rebel.getLocation());
      if (!locationErrors.isEmpty()) errors.addAll(locationErrors);

      if (rebel.getLocation().getLatitude() == location.getLatitude()
          && rebel.getLocation().getLongitude() == location.getLongitude()
          && Objects.equals(rebel.getLocation().getGalaxyName(), location.getGalaxyName()))
        errors.add("Rebel's new location must be different from its previous one.");

      return errors;
    }
}
