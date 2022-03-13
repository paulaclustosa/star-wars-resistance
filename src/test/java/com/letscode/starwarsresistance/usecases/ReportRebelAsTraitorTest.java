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
class ReportRebelAsTraitorTest {

    @Mock RebelValidator rebelValidator;
    @Mock RebelPersistenceGateway rebelPersistenceGateway;
    @InjectMocks ReportRebelAsTraitor reportRebelAsTraitor;

    @Test
    void shouldNotUpdateWhenValidationFails(){
        Rebel rebel = Rebel.builder().build();

        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of("rebel validation error"));

        Assertions.assertThrows(BusinessValidationException.class, () -> reportRebelAsTraitor.execute(rebel));
    }

    @Test
    void shouldUpdateWhenValidationSucceeds(){
        Rebel rebel = Rebel.builder().build();
        final int reportsBefore = rebel.getNumberOfReportsAsTraitor();

        Mockito.when(rebelValidator.validate(rebel)).thenReturn(List.of());

        rebel.setNumberOfReportsAsTraitor(0);

        reportRebelAsTraitor.execute(rebel);

        Assertions.assertFalse(rebel.isTraitor());
        Assertions.assertEquals(reportsBefore + 1, rebel.getNumberOfReportsAsTraitor());

        rebel.setNumberOfReportsAsTraitor(2);

        reportRebelAsTraitor.execute(rebel);

        Assertions.assertTrue(rebel.isTraitor());

        Mockito.verify(rebelPersistenceGateway, Mockito.times(2)).save(rebel);
    }

}