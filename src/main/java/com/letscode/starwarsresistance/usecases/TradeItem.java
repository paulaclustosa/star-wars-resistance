package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.CreateRebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeItem {
    private final CreateRebelValidator createRebelValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute(Rebel rebel, Inventory itemsRequired, Inventory itemsRejected){
        //verify rebel
        val rebelErrors = createRebelValidator.validate(rebel);
        if (!rebelErrors.isEmpty()) throw new BusinessValidationException(rebelErrors);

        //verify inventory
        //verify if rebel has itemsRejected
        //if (!rebel.hasItems()) throw new BusinessValidatationException(List.of("Rebel doesn't own all items to reject"));

        //verify points
        if (itemsRequired.sumPoints() == itemsRejected.sumPoints()) {
            rebel.setInventory(itemsRequired);
        }else {
            throw new BusinessValidationException(List.of("Items cannot be traded because their values are not equal"));
        }
        return rebelPersistenceGateway.save(rebel);
    }
}
