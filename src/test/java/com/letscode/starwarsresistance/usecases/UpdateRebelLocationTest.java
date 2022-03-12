package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings
class UpdateRebelLocationTest {

    @InjectMocks
    UpdateRebelLocation updateRebelLocation;
    @Mock RebelPersistenceGateway rebelPersistenceGateway;

    @Test
    void shouldUpdateWhenValidationSucceeds() {
        Rebel rebel = Rebel.builder().build();
        Location location = Location.builder().build();
        Assertions.assertNotEquals(rebel.getLocation(), location);
        updateRebelLocation.execute(rebel, location);
        Mockito.verify(rebelPersistenceGateway).save(rebel);
        Assertions.assertEquals(rebel.getLocation(), location);
    }
}