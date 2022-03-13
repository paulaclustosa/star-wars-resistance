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

        reportRebelAsTraitor.execute(rebel);

        Assertions.assertEquals(reportsBefore + 1, rebel.getNumberOfReportsAsTraitor());

        if (rebel.getNumberOfReportsAsTraitor() >= 3){
            Assertions.assertEquals(true, rebel.isTraitor());
        }else{
            Assertions.assertEquals(false, rebel.isTraitor());
        }

        Mockito.verify(rebelPersistenceGateway).save(rebel);
    }

}