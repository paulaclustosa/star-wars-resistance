package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.UpdateRebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRebelAsTraitor {
    private final UpdateRebelValidator updateRebelValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute (Rebel rebel){
        val rebelErrors = updateRebelValidator.validate(rebel);

        if (!rebelErrors.isEmpty()) throw new BusinessValidationException(rebelErrors);
        rebel.setNumberOfReportsAsTraitor(rebel.getNumberOfReportsAsTraitor()+1);

        if (!rebel.isTraitor() && rebel.getNumberOfReportsAsTraitor() >= 3){
            rebel.setTraitor(true);
        }

        return rebelPersistenceGateway.save(rebel);
    }
}
