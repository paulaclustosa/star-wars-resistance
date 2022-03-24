package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.UpdateRebelLocationValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRebelLocation {
    private final UpdateRebelLocationValidator updateRebelLocationValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute(Rebel rebel, Location location) {
        val errors = updateRebelLocationValidator.validate(rebel, location);
        if (!errors.isEmpty()) throw new BusinessValidationException(errors);

        rebel.setLocation(location);
        return rebelPersistenceGateway.save(rebel);
    }
}
