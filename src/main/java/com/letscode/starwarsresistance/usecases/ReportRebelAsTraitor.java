package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.RebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRebelAsTraitor {
    private final RebelValidator createRebelValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute (Rebel rebel){
        val rebelErrors = createRebelValidator.validate(rebel);
        if (!rebelErrors.isEmpty()) throw new BusinessValidationException(rebelErrors);

        if (!rebel.isTraitor()){
            rebel.setTraitor(true);
        }
        rebel.setNumberOfReportsAsTraitor(rebel.getNumberOfReportsAsTraitor()+1);
        return rebelPersistenceGateway.save(rebel);
    }
}
