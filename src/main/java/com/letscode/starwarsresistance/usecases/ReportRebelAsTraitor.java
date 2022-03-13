package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidatationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.CreateRebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRebelAsTraitor {
    private final CreateRebelValidator createRebelValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute (Rebel rebel){
        val rebelErrors = createRebelValidator.validate(rebel);
        if (!rebelErrors.isEmpty()) throw new BusinessValidatationException(rebelErrors);

        if (!rebel.isTraitor()){
            rebel.setTraitor(true);
        }
        rebel.setNumberOfReportsAsTraitor(rebel.getNumberOfReportsAsTraitor()+1);
        return rebelPersistenceGateway.save(rebel);
    }
}
