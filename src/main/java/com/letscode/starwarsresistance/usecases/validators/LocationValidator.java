package com.letscode.starwarsresistance.usecases.validators;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import com.letscode.starwarsresistance.domains.enums.Item;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LocationValidator {
    public List<String> validate(Location location) {
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(String.valueOf(location.getLatitude()))) errors.add("Rebel's location is mandatory (latitude).");
        if (!StringUtils.hasText(String.valueOf(location.getLongitude()))) errors.add("Rebel's location is mandatory (longitude).");
        if (!StringUtils.hasText(location.getGalaxyName())) errors.add("Rebel's location is mandatory (galaxy's name).");

        return errors;
    }
}
