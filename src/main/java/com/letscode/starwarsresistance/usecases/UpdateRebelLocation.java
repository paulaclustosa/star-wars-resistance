package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRebelLocation {
    private final RebelPersistenceGateway rebelPersistenceGateway;

    public Rebel execute(Rebel rebel, Location location){
        //location valid
        //rebel exists
        rebel.setLocation(location);
        return rebelPersistenceGateway.save(rebel);
    }
}
