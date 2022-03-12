package com.letscode.starwarsresistance.gateways.persistence;

import com.letscode.starwarsresistance.domains.entities.Rebel;

import java.util.List;
import java.util.Optional;

public interface RebelPersistenceGateway {

  Rebel save(Rebel rebel);

  void delete(Rebel rebel);

  boolean existsById(Long id);

  Optional<Rebel> findById(Long id);

  List<Rebel> findAll();

}
