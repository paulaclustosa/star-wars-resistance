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

        Rebel rebel1Updated = trade(rebel1, rebel1Items, rebel2Items);
        Rebel rebel2Updated = trade(rebel2, rebel2Items, rebel1Items);

        return List.of(rebelPersistenceGateway.save(rebel1Updated), rebelPersistenceGateway.save(rebel2Updated));
    }

    private Rebel trade(Rebel rebel, Inventory itemsToRemove, Inventory itemsToInclude) {
        rebel.getInventory().setAmmunitionAmount(rebel.getInventory().getAmmunitionAmount() - itemsToRemove.getAmmunitionAmount() + itemsToInclude.getAmmunitionAmount());
        rebel.getInventory().setWeaponAmount(rebel.getInventory().getWeaponAmount() - itemsToRemove.getWeaponAmount() + itemsToInclude.getWeaponAmount());
        rebel.getInventory().setWaterAmount(rebel.getInventory().getWaterAmount() - itemsToRemove.getWaterAmount() + itemsToInclude.getWaterAmount());
        rebel.getInventory().setFoodAmount(rebel.getInventory().getFoodAmount() - itemsToRemove.getFoodAmount() + itemsToInclude.getFoodAmount());

        return rebel;
    }
}
