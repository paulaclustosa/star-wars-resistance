package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.RebelValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

@MockitoSettings
class CreateRebelTest {

    @Mock RebelValidator rebelValidator;
    @Mock RebelPersistenceGateway rebelPersistenceGateway;
    @InjectMocks CreateRebel createRebel;

    @Test
    void shouldCreateWhenValidationSucceeds() {
        Rebel rebel = Rebel.builder().build();
        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of());
        createRebel.execute(rebel);
        Mockito.verify(rebelPersistenceGateway).save(rebel);
    }

    @Test
    void shouldNotCreateWhenValidationFails() {
        Rebel rebel = Rebel.builder().build();
        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of("Rebel validation error"));
        Assertions.assertThrows(BusinessValidationException.class, () -> createRebel.execute(rebel));
    }
}