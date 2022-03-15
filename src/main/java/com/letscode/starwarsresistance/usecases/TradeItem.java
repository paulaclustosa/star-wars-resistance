package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.InventoryValidator;
import com.letscode.starwarsresistance.usecases.validators.UpdateRebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeItem {
    private final UpdateRebelValidator updateRebelValidator;
    private final InventoryValidator inventoryValidator;
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public List<Rebel> execute(Rebel rebel1, Inventory rebel1Items, Rebel rebel2, Inventory rebel2Items){
        //verify rebel
        val rebelErrors1 = updateRebelValidator.validate(rebel1);
        if (!rebelErrors1.isEmpty()) throw new BusinessValidationException(rebelErrors1);

        val rebelErrors2 = updateRebelValidator.validate(rebel2);
        if (!rebelErrors2.isEmpty()) throw new BusinessValidationException(rebelErrors2);

        //verify inventory
        val inventoryErrors1 = inventoryValidator.validate(rebel1Items);
        if (!inventoryErrors1.isEmpty()) throw new BusinessValidationException(inventoryErrors1);

        val inventoryErrors2 = inventoryValidator.validate(rebel2Items);
        if (!inventoryErrors2.isEmpty()) throw new BusinessValidationException(inventoryErrors2);

        //verify if rebel has itemsRejected
        if (!rebel1.hasItems(rebel1Items)) throw new BusinessValidationException(List.of(rebel1.getName() + " doesn't own all items to reject"));
        if (!rebel2.hasItems(rebel2Items)) throw new BusinessValidationException(List.of(rebel2.getName() + " doesn't own all items to reject"));

        //verify points
        rebel1.getInventory().setPoints();
        rebel2.getInventory().setPoints();

        if (rebel1.getInventory().getPoints() != rebel2.getInventory().getPoints()) throw new BusinessValidationException(List.of("Items cannot be traded because their values are not equal"));

        rebel1.getInventory().setAmmunitionAmount(rebel2Items.getAmmunitionAmount());
        rebel1.getInventory().setWeaponAmount(rebel2Items.getWeaponAmount());
        rebel1.getInventory().setWaterAmount(rebel2Items.getWaterAmount());
        rebel1.getInventory().setFoodAmount(rebel2Items.getFoodAmount());

        rebel2.getInventory().setAmmunitionAmount(rebel1Items.getAmmunitionAmount());
        rebel2.getInventory().setWeaponAmount(rebel1Items.getWeaponAmount());
        rebel2.getInventory().setWaterAmount(rebel1Items.getWaterAmount());
        rebel2.getInventory().setFoodAmount(rebel1Items.getFoodAmount());

        return List.of(rebelPersistenceGateway.save(rebel1), rebelPersistenceGateway.save(rebel2));
    }
}
