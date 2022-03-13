package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.RebelValidator;
import com.letscode.starwarsresistance.usecases.validators.LocationValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

@MockitoSettings
class UpdateRebelLocationTest {

    @Mock LocationValidator locationValidator;
    @Mock RebelValidator rebelValidator;
    @Mock RebelPersistenceGateway rebelPersistenceGateway;
    @InjectMocks UpdateRebelLocation updateRebelLocation;

    @Test
    void shouldUpdateWhenValidationSucceeds() {

        Rebel rebel = Rebel.builder().build();
        Location location = Location.builder().build();

        Assertions.assertNotEquals(rebel.getLocation(), location);

        Mockito.when(locationValidator.validate(location)).thenReturn(List.of());
        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of());

        updateRebelLocation.execute(rebel, location);
        Assertions.assertEquals(rebel.getLocation(), location);

        Mockito.verify(rebelPersistenceGateway).save(rebel);
    }

    @Test
    void shouldNotUpdateWhenRebelValidationFails() {

        Rebel rebel = Rebel.builder().build();
        Location location = Location.builder().build();

        Assertions.assertNotEquals(rebel.getLocation(), location);

        Mockito.when(locationValidator.validate(location)).thenReturn(List.of());
        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of("rebel validation error"));

        Assertions.assertThrows(BusinessValidationException.class, () -> updateRebelLocation.execute(rebel, location));
    }

    @Test
    void shouldNotUpdateWhenLocationValidationFails() {

        Rebel rebel = Rebel.builder().build();
        Location location = Location.builder().build();

        Assertions.assertNotEquals(rebel.getLocation(), location);

        Mockito.when(locationValidator.validate(location)).thenReturn(List.of("location validation error"));

        Assertions.assertThrows(BusinessValidationException.class, () -> updateRebelLocation.execute(rebel, location));
    }
}