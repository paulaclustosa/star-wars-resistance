package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.CreateRebelValidator;
import com.letscode.starwarsresistance.usecases.validators.LocationValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRebelLocation {
    private final CreateRebelValidator createRebelValidator;
    private final LocationValidator locationValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute(Rebel rebel, Location location){

        val locationErrors = locationValidator.validate(location);
        if (!locationErrors.isEmpty()) throw new BusinessValidationException(locationErrors);

        val rebelErrors = createRebelValidator.validate(rebel);
        if (!rebelErrors.isEmpty()) throw new BusinessValidationException(rebelErrors);

        rebel.setLocation(location);
        return rebelPersistenceGateway.save(rebel);
    }
}
