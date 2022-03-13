package com.letscode.starwarsresistance.usecases;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.exceptions.BusinessValidationException;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.validators.CreateRebelValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRebel {

  private final CreateRebelValidator createRebelValidator;
  private final RebelPersistenceGateway rebelPersistenceGateway;

  public Rebel execute(Rebel rebel) {
    val errors = createRebelValidator.validate(rebel);

    if (!errors.isEmpty()) throw new BusinessValidationException(errors);

    return rebelPersistenceGateway.save(rebel);
  }

}
