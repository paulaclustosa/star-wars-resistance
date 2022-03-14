package com.letscode.starwarsresistance.gateways.persistence.repository;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
//import com.letscode.starwarsresistance.gateways.persistence.repository.RebelH2Repository;
//import com.letscode.starwarsresistance.gateways.persistence.repository.entities.RebelEntity;
import com.letscode.starwarsresistance.gateways.persistence.repository.entities.RebelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RebelPersistenceH2GatewayImpl implements RebelPersistenceGateway {

  private final RebelH2Repository rebelH2Repository;

  @Override
  public Rebel save(Rebel rebel) {
    RebelEntity rebelEntity = rebelH2Repository.save(new RebelEntity(rebel));
    return rebelEntity.toRebel();
  }

  @Override
  public void delete(Rebel rebel) {
    rebelH2Repository.delete(new RebelEntity(rebel));
  }

  @Override
  public boolean existsById(Long id) {
    return rebelH2Repository.existsById(id);
  }

  @Override
  public Optional<Rebel> findById(Long id) {
    return rebelH2Repository.findById(id).map(RebelEntity::toRebel);
  }

  @Override
  public List<Rebel> findAll() {
    List<Rebel> result = new ArrayList<>();
    rebelH2Repository.findAll().forEach(rebelEntity -> result.add(rebelEntity.toRebel()));
    return result;
  }

}
